import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {LocalStorageService} from "ngx-webstorage";
import {Observable} from "rxjs";
import {Lesson} from "../Form/Lesson";
import {LessonDetail} from "../Form/LessonDetail";
import {Question} from "../Form/Question";
import {Status} from "../Form/status";


@Injectable({
  providedIn: 'root'
})
export class LessonService {

  private url = 'http://localhost:8082/api/lesson';

  constructor(private http: HttpClient) {
  }

  checkSubscription(token: string): Observable<Status>{
    return this.http.get<Status>("http://localhost:8082/api/subscription/" + token)
  }

  getAllLesson(): Observable<Array<Lesson>> {
    return this.http.get<Array<Lesson>>(this.url);
  }

  getDetailLesson(IdPost: number): Observable<LessonDetail> {
    return this.http.get<LessonDetail>(this.url +'/'+ IdPost);
  }

  getQuestionOfLesson(paramElement: any): Observable<Array<Question>>{
    return this.http.get<Array<Question>>(this.url +'/question/'+ paramElement);
  }

  // postAnswer2(question: Array<Question>, IdPost: number): Observable<Status> {
  //   return this.http.post<Status>(this.url + '/' + IdPost, Question);
  // }
  postAnswer(question: Array<Question>, id: number): Observable<Status> {
    return this.http.put<Status>(this.url + '/' + id, question);
  }
}
