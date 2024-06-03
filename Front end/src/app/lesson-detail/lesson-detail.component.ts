import { Component, OnInit } from '@angular/core';
import {LessonService} from "../service/lesson.service";
import {ActivatedRoute, Router} from "@angular/router";
import {LessonDetail} from "../Form/LessonDetail";
import { Question } from '../Form/Question';
import {AuthService} from "../service/auth.service";


@Component({
  selector: 'app-lesson-detail',
  templateUrl: './lesson-detail.component.html',
  styleUrls: ['./lesson-detail.component.css']
})
export class LessonDetailComponent implements OnInit {
  public Lesson: LessonDetail;
  public Question: Array<Question>;
  public id: number;
  constructor(private router: ActivatedRoute, private lessonService: LessonService, private Router: Router, public authService: AuthService) { }
  ngOnInit(): void {
    if(!this.authService.isAuth()){
      this.Router.navigateByUrl('/auth/login')
      alert('Bạn cần phải đăng nhập để sử dụng chức năng này')
    }else {
      this.router.params.subscribe(param => {
        this.id = param['id']
        this.lessonService.getDetailLesson(param['id']).subscribe(
          data => {
            this.Lesson = data
            if(this.Lesson.level == null){
              if(window.confirm('Bạn có muốn trở thành thành viên vip để xem bài học này không')){
                this.Router.navigateByUrl('/checkout')
              }else{
                this.Router.navigateByUrl('/lesson');
              }
            }
          }
        );
        this.lessonService.getQuestionOfLesson(param['id']).subscribe(
          data => {
            this.Question = data
          }
        );

      });
    }
  }

  OnSubmit() {
    this.lessonService.postAnswer(this.Question, this.id).subscribe(
      data => {
        alert(data.message)
        window.location.reload()
      }
    )
  }
}
