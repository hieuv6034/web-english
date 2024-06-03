import { Component, OnInit } from '@angular/core';
import {LessonService} from "../service/lesson.service";
import {Lesson} from "../Form/Lesson";
import {Observable} from "rxjs";
import {LocalStorageService} from "ngx-webstorage";

@Component({
  selector: 'app-lesson',
  templateUrl: './lesson.component.html',
  styleUrls: ['./lesson.component.css']
})
export class LessonComponent implements OnInit {
  role: string
  constructor(private lessonService: LessonService, private localStorage: LocalStorageService) { }
  Lesson: Array<Lesson>
  ngOnInit(): void {
    this.lessonService.getAllLesson().subscribe(
      data => {
        this.Lesson = data
      }
    )
    this.role = this.localStorage.retrieve('role')
  }

}
