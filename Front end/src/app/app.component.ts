import {Component,OnInit} from '@angular/core';
import {LocalStorageService} from "ngx-webstorage";
import {Login} from "./Form/Login";
import {AuthService} from "./service/auth.service";



@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent implements OnInit{
  Infor: Login = new Login(this.localStorage.retrieve('username'), "");
  hidden: any = true;
  hiddem2: any = true;
  constructor(public localStorage: LocalStorageService, public authService: AuthService) {
  }

  ngOnInit(): void {
    if(this.localStorage.retrieve('role') == 'ROLE_ADMIN'){
      this.hiddem2 = false
    }
    this.hidden =  this.authService.isAuth();
  }
  logout(){
    this.authService.logout(this.Infor).subscribe(
      data => {
        if(data.status = "ok"){
          alert(data.message)
          window.location.reload()
        }else {
          alert(data.message)
        }
      },
      error =>{
        alert(error.message())
      }
    );
  }
}
