import {Component, OnInit} from '@angular/core';
import {SignIn} from '../../Form/SignIn';
import {Router} from '@angular/router';
import {AuthService} from "../../service/auth.service";

@Component({
  selector: 'app-register',
  templateUrl: './register.component.html',
  styleUrls: ['./register.component.scss']
})
export class RegisterComponent implements OnInit {

  constructor(private authservice: AuthService, private router: Router) {
  }

  ngOnInit(): void {
  }

  public model = new SignIn('', '', '', "");

  onSubmit(inFor: SignIn): void {
    if(inFor.confirmpassword === inFor.password){
      this.authservice.SignIn(inFor).subscribe(
      data => {
        if(data.status == "ok") {
          this.router.navigateByUrl("/auth/login");
        }else{
          alert(data.message)
        }
      },
      error => {
        alert(error.message);
      }
      );
    }else{
      alert("xác nhận mật khẩu không chính xác")
    }

  }
}
