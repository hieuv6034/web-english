import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { FormsModule } from '@angular/forms';
import {HTTP_INTERCEPTORS, HttpClientModule} from '@angular/common/http';
import {Ng2Webstorage} from 'ngx-webstorage';
import {DropdownModule} from 'primeng/dropdown';
import {EditorModule} from 'primeng/editor';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {ButtonModule} from 'primeng/button';
import {CheckboxModule} from 'primeng/checkbox';
import {HttpClientInterceptor} from "./htttp-client";
import { AuthComponent } from './auth/auth.component';
import { LoginComponent } from './auth/login/login.component';
import { RegisterComponent } from './auth/register/register.component';
import { HomeComponent } from './home/home.component';
import {TabMenuModule} from 'primeng/tabmenu';
import {RadioButtonModule} from 'primeng/radiobutton';
import {PasswordModule} from 'primeng/password';
import {InputTextModule} from 'primeng/inputtext';
import { LessonComponent } from './lesson/lesson.component';
import { LessonDetailComponent } from './lesson-detail/lesson-detail.component';
import { ExamComponent } from './exam/exam.component';
import { ManageComponent } from './manage/manage.component';
import { CancelComponent } from './cancel/cancel.component';
import { SucessComponent } from './sucess/sucess.component';
import { CheckoutComponent } from './checkout/checkout.component';
import { ForgotComponent } from './auth/forgot/forgot.component';
import { ChangePassComponent } from './auth/change-pass/change-pass.component';
import { RankComponent } from './rank/rank.component';
import {MessageModule} from "primeng/message";
import {MessagesModule} from "primeng/messages";
import {TableModule} from "primeng/table";

@NgModule({
  declarations: [
    AppComponent,
    AuthComponent,
    LoginComponent,
    RegisterComponent,
    HomeComponent,
    LessonComponent,
    LessonDetailComponent,
    ExamComponent,
    ManageComponent,
    CancelComponent,
    SucessComponent,
    CheckoutComponent,
    ForgotComponent,
    ChangePassComponent,
    RankComponent,
  ],
    imports: [
        BrowserModule,
        AppRoutingModule,
        InputTextModule,
        FormsModule,
        HttpClientModule,
        Ng2Webstorage.forRoot(),
        DropdownModule,
        EditorModule,
        BrowserAnimationsModule,
        ButtonModule,
        CheckboxModule,
        TabMenuModule,
        RadioButtonModule,
        PasswordModule,
        MessageModule,
        MessagesModule,
        TableModule,
    ],
  providers: [{provide: HTTP_INTERCEPTORS, useClass: HttpClientInterceptor, multi: true}],
  bootstrap: [AppComponent]
})
export class AppModule { }
