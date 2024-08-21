import { HttpClient, HttpHeaders } from '@angular/common/http';
import { inject, Injectable } from '@angular/core';

import { environment } from '../../../../environments/environment';
import { Observable } from 'rxjs/internal/Observable';
import { PartyContainer, RequestPartySearch, ResponseError } from './party.models';
import { catchError, tap } from 'rxjs';

@Injectable({
    providedIn: 'root'
})
export class PartyService {
    private http = inject(HttpClient);

    private baseProxyAccessUrl: string = environment.urlAuthProxyServer;

    public searchParties(search: RequestPartySearch, page: number = 0): Observable<PartyContainer> {
        const url = this.baseProxyAccessUrl + '/party/search/?detail=true';
        const context = {
            headers: new HttpHeaders().set('Content-Type', 'application/json'),
            params: {
                page: page
            }
        };
        const body = search;

        // Remove body members with no search/filter data
        if (body.name === '') delete body.name;
        if (body.lastName === '') delete body.lastName;
        if (body.dateOfBirth === '') delete body.dateOfBirth;
        if (body.status === '') delete body.status;
        if (body.email === '') delete body.email;
        if (body.documentId === '') delete body.documentId;
        if (body.nationality === '') delete body.nationality;

        return this.http.post<PartyContainer>(url, body, context)
            .pipe(
                catchError(error => {
                    console.log("Error retrieving party data: " + error.code)
                    // Propagate error to caller method for form handling
                    const responseError: ResponseError = {
                        code: error.status,
                        message: error.message
                    };
                    throw responseError;
                }),
                tap(data => {
                    //console.log(data);
                })
            );
    }
}