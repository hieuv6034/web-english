import { Component, OnInit } from '@angular/core';
import {LessonService} from "../service/lesson.service";
import {ActivatedRoute} from "@angular/router";
import {Status} from "../Form/status";
import {LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-sucess',
  templateUrl: './sucess.component.html',
  styleUrls: ['./sucess.component.css']
})
export class SucessComponent implements OnInit {
  token: string
  constructor(private router: ActivatedRoute, private lessonService: LessonService, private localStorageService: LocalStorageService) { }
  status: Status
  ngOnInit(): void {
    this.router.params.subscribe(param => {
      this.lessonService.checkSubscription(param['token']).subscribe(
        data => {
          this.status = data
          console.log(this.status)
          if(this.localStorageService.retrieve('role') == 'ROLE_USER'){
            this.localStorageService.store('role', 'ROLE_VIP_USER')
          }
        }
      )
    })
  }

}
