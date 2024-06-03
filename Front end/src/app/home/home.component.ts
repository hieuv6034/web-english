import {Component, OnDestroy, OnInit} from '@angular/core';
import {Message} from "../Form/Message";
import {AuthService} from "../service/auth.service";
import {ManageService} from "../service/manage.service";

@Component({
  selector: 'app-home',
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit, OnDestroy {
  constructor(public authService: AuthService, private manageService: ManageService) { }
  user
  ngOnInit(): void {
    this.manageService.getUser().subscribe(
      data => {
        this.user = data
      }
    )
  }

  ngOnDestroy() {

  }


}
