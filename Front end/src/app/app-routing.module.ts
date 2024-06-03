import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import {LoginComponent} from "./auth/login/login.component";
import {RegisterComponent} from "./auth/register/register.component";
import {HomeComponent} from "./home/home.component";
import {LessonComponent} from "./lesson/lesson.component";
import {LessonDetailComponent} from "./lesson-detail/lesson-detail.component";
import {ExamComponent} from "./exam/exam.component";
import {ManageComponent} from "./manage/manage.component";
import {CancelComponent} from "./cancel/cancel.component";
import {SucessComponent} from "./sucess/sucess.component";
import {CheckoutComponent} from "./checkout/checkout.component";
import {ForgotComponent} from "./auth/forgot/forgot.component";
import {ChangePassComponent} from "./auth/change-pass/change-pass.component";
import {RankComponent} from "./rank/rank.component";
const routes: Routes = [
  { path: '', pathMatch: 'full' ,component: HomeComponent},
  { path: 'auth/register', component: RegisterComponent },
  { path: 'auth/login', component: LoginComponent },
  { path: 'lesson', component: LessonComponent },
  { path: 'lesson/:id', component: LessonDetailComponent },
  { path: 'exam', component: ExamComponent },
  { path: 'manage', component: ManageComponent},
  { path: 'cancel', component: CancelComponent },
  { path: 'success/:token', component: SucessComponent },
  { path: 'forgot', component: ForgotComponent },
  { path: 'change', component: ChangePassComponent },
  { path: 'rank', component: RankComponent },
  {
    path: 'checkout',
    component: CheckoutComponent,
  },
];

@NgModule({
  imports: [RouterModule.forRoot(routes, { relativeLinkResolution: 'legacy' })],
  exports: [RouterModule]
})
export class AppRoutingModule { }
