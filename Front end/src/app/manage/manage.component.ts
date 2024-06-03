import { Component, OnInit } from '@angular/core';
import {ManageService} from "../service/manage.service";
import {User} from "../Form/user";
import {LocalStorageService} from "ngx-webstorage";
import {ActivatedRoute, Router} from "@angular/router";
@Component({
  selector: 'app-manage',
  templateUrl: './manage.component.html',
  styleUrls: ['./manage.component.css']
})
export class ManageComponent implements OnInit {
  public user: Array<User>
  name: string
  constructor(private manageService: ManageService, private Router: Router, private localStorageService: LocalStorageService) { }
  banedUser: User = new User()
  ngOnInit(): void {
    if(this.localStorageService.retrieve('role') != 'ROLE_ADMIN'){
        this.Router.navigateByUrl("/")
    }else{
      this.manageService.getAllUser().subscribe(
        data => {
          this.user = data
        }
      )
    }

  }

  onSubmit(ID: number) {
    this.banedUser.userID = ID
    this.manageService.BanUser(this.banedUser).subscribe(data =>{
      alert(data.message)
      window.location.reload()
    })
  }

  onSearch() {
    this.manageService.getbyKey(this.name).subscribe(data =>{
      this.user = data
    })
  }
}
