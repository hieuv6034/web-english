import { Component, OnInit } from '@angular/core';
import {AuthService} from "../../service/auth.service";
import {Router} from "@angular/router";

@Component({
  selector: 'app-forgot',
  templateUrl: './forgot.component.html',
  styleUrls: ['./forgot.component.css']
})
export class ForgotComponent implements OnInit {
  model: string;
  status = true;
  constructor(private authService: AuthService, private router: Router) { }

  ngOnInit(): void {

  }

  onSubmit(model: string) {
    this.status = false
    this.authService.forgot(model).subscribe(data => {
      this.status = true
      alert(data.message)
      if(data.status == 'ok'){
        this.status = true
        this.router.navigateByUrl('/')
      }
    })
  }
}
