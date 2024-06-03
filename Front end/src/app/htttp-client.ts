import {HttpInterceptor, HttpRequest, HttpHandler, HttpEvent, HttpErrorResponse} from '@angular/common/http';
import {Observable, throwError} from 'rxjs';
import { LocalStorageService } from 'ngx-webstorage';
import { Injectable } from '@angular/core';
import {catchError} from "rxjs/operators";
import {AuthService} from "./service/auth.service";
import {Router} from "@angular/router";

@Injectable()
export class HttpClientInterceptor implements HttpInterceptor {
    constructor(private localStorage: LocalStorageService,  private router: Router) {

    }

  intercept(req: HttpRequest<any>,
            next: HttpHandler): Observable<HttpEvent<any>> {

    const token = this.localStorage.retrieve('authenticationToken');
    if (token != null) {
      req = req.clone(
        {
          setHeaders: {
            'Content-Type': 'application/json; charset=utf-8',
            'Accept': 'application/json',
            Authorization: `Bearer ${token}`
          }
        }
      )
    }
    return next.handle(req);
  }

}
