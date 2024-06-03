import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

import {ChangePass} from "../../Form/ChangePass";
@Component({
  selector: 'app-change-pass',
  templateUrl: './change-pass.component.html',
  styleUrls: ['./change-pass.component.css']
})
export class ChangePassComponent implements OnInit {

  public model = new ChangePass('', '', '');

  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

  }

  onSubmit() {

    this.authService.changePass(this.model).subscribe(data => {
      alert(data.message)
      if(data.status == 'ok'){
        this.router.navigateByUrl('/')
      }
    })
  }


}
