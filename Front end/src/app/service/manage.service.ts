import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {User} from "../Form/user";
import {Rank} from "../Form/Rank";
import {Status} from "../Form/status";
@Injectable({
  providedIn: 'root'
})
export class ManageService {
  private url = 'http://localhost:8082/api/Admin';
  constructor(private http: HttpClient) { }
  getAllUser(): Observable<Array<User>> {
    return this.http.get<Array<User>>(this.url + "/user");
  }
  getUser(): Observable<User> {
    return this.http.get<User>("http://localhost:8082/api/user");
  }

  getRankUser(): Observable<Array<User>> {
    return this.http.get<Array<User>>("http://localhost:8082/api/user/rank");
  }

  getRankOfMe(): Observable<Rank>{
    return this.http.get<Rank>("http://localhost:8082/api/you/rank")
  }
  BanUser(user: User): Observable<Status>{
    return this.http.put<Status>("http://localhost:8082/api/Admin/user/"+user.userID, user)
  }
  getbyKey(name: string): Observable<Array<User>> {
    return this.http.get<Array<User>>(this.url + "/user/" + name);
  }
}
