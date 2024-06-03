import { Component, OnInit } from '@angular/core';
import {ExamService} from "../service/exam.service";
import {Question} from "../Form/Question";
import {Router} from "@angular/router";
import {AuthService} from "../service/auth.service";

@Component({
  selector: 'app-exam',
  templateUrl: './exam.component.html',
  styleUrls: ['./exam.component.css']
})
export class ExamComponent implements OnInit {

  public id: number
  public question: Array<Question>
  constructor(private examService: ExamService, private router: Router, private Router: Router, public authService: AuthService){ }

  ngOnInit(): void {
    if(!this.authService.isAuth()){
      this.Router.navigateByUrl('/auth/login')
      alert('Bạn cần phải đăng nhập để sử dụng chức năng này')
    }else {
      if (window.confirm('Do you want to take the test? ' +
        'You will have 15 minutes')) {
        this.examService.getExam().subscribe(
          data => {
            if(data.status === 'OK'){
              this.examService.getQuestion().subscribe(
                data => {
                  this.question = data
                }
              )
            }else{
              alert(data.message)
              this.router.navigateByUrl('');
            }
          }
        )
      }else{
        this.router.navigateByUrl("");
      }
    }
  }

  OnSubmit() {
    this.examService.postAnswer(this.question).subscribe(
      data => {
        alert(data.message)
        this.router.navigateByUrl('');
      }
    )
  }
}
