import { BrowserModule } from '@angular/platform-browser';
import { NgModule } from '@angular/core';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { LogInComponent } from './log-in/log-in.component';
import { SignUpComponent } from './sign-up/sign-up.component';
import { LoggedInComponent } from './logged-in/logged-in.component';
import { SpringSecurityCrudService } from './spring-security-crud.service';
import { HttpClientModule } from '@angular/common/http'; 
import { ForgotPasswordComponent } from './forgot-password/forgot-password.component';
import { SharedServiceUserService } from './shared-service-user.service';


@NgModule({
  declarations: [
    AppComponent,
    LogInComponent,
    SignUpComponent,
    LoggedInComponent,
    ForgotPasswordComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    ReactiveFormsModule
  ],
  providers: [SpringSecurityCrudService,SharedServiceUserService],
  bootstrap: [AppComponent]
})
export class AppModule { }
