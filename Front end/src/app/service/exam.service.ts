import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Question} from "../Form/Question";
import {Status} from "../Form/status";

@Injectable({
  providedIn: 'root'
})
export class ExamService {
  private url = 'http://localhost:8082/api/exam';

  constructor(private http: HttpClient) { }

  getExam(): Observable<Status> {
    return this.http.get<Status>(this.url);
  }

  getQuestion(): Observable<Array<Question>>{
    return this.http.get<Array<Question>>(this.url +'/question');
  }

  postAnswer(question: Array<Question>): Observable<Status>{
    return this.http.post<Status>(this.url, question);
  }
}
