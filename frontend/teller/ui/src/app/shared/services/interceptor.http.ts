import { HttpRequest, HttpHandlerFn, HttpEvent } from "@angular/common/http";
import { Observable } from "rxjs";

export function secureHttpInterceptor(req: HttpRequest<unknown>, next: HttpHandlerFn): Observable<HttpEvent<unknown>> {
    console.log(req.url);
    return next(req);
}