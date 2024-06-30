import { HttpClient, HttpParams, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';

import { Observable, catchError, retry } from 'rxjs';

@Injectable({
  providedIn: 'root'
})
export class AccessService {

  constructor(private http: HttpClient) { }

  getAccessToken(username: string, password: string, rememberMe: boolean): void {
    const url2 = 'http://localhost:9000/realms/modernbank/.well-known/openid-configuration';
    const url = 'http://localhost:9000/realms/modernbank/protocol/openid-connect/token';
    
    console.log(
      `User: ${username}, Password: ${password}, RememberMe: ${rememberMe}`
    );

    //const data = await fetch(url);
    const body = new HttpParams()
      //.set('username', username)
      //.set('password', password)
      .set('username', 'johndoe')
      .set('password', 'password')
      .set('grant_type', 'password')
      .set('client_id', 'admin-cli');


    const headers = { 'Access-Control-Allow-Origin': '*'};
    this.http.post<string>(url, body.toString, {
      headers: new HttpHeaders()
        .set('Content-Type', 'application/x-www-form-urlencoded')
    })
      //.pipe(retry(2), catchError(this.handleError))
      .subscribe(response => {
        console.log(response);
      });
  }

  handleError(err: any, caught: Observable<string>): Observable<any> {
    console.log("Error");
    return caught;
  }
}
