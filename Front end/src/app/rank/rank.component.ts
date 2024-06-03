import { Component, OnInit } from '@angular/core';
import {User} from "../Form/user";
import {ManageService} from "../service/manage.service";
import {Rank} from "../Form/Rank";
import {AuthService} from "../service/auth.service";
import {ActivatedRoute, Router} from "@angular/router";

@Component({
  selector: 'app-rank',
  templateUrl: './rank.component.html',
  styleUrls: ['./rank.component.css']
})
export class RankComponent implements OnInit {

  public user: Array<User>
  rank: Rank
  constructor(private manageService: ManageService, public authService: AuthService, private Router: Router) { }

  ngOnInit(): void {
    if(this.authService.isAuth()){
      this.manageService.getRankUser().subscribe(
        data => {
          this.user = data
        }
      )
      this.manageService.getRankOfMe().subscribe(
        data => {
          this.rank = data
        }
      )
    }else{
      alert('Bạn cần phải đăng nhập để xem được bảng xếp hạng')
      this.Router.navigateByUrl('auth/login')
    }
  }

}
