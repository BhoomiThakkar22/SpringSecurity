import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Validators, FormGroup, FormBuilder } from '@angular/forms';
import { SpringSecurityCrudService } from '../spring-security-crud.service';
import { User } from '../User';
import { SharedServiceUserService } from '../shared-service-user.service';

@Component({
  selector: 'app-forgot-password',
  templateUrl: './forgot-password.component.html',
  styleUrls: ['./forgot-password.component.css']
})
export class ForgotPasswordComponent implements OnInit {
  user: User = null;
  forgotPasswordForm: FormGroup;
  submitted: boolean = false;
  submittedNewPassword: boolean = false;
  submittedOTP: boolean = false;
  resetPasswordForm: FormGroup;
  checkOTP: boolean = false;
  getOTP: boolean = false;
  checkOTPForm: FormGroup;
  constructor(private route: Router, private formBuilder: FormBuilder, private springSecurity: SpringSecurityCrudService, private sharedUserService: SharedServiceUserService) {

  }

  ngOnInit() {
    this.forgotPasswordForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]]
    });
    this.checkOTPForm = this.formBuilder.group({
      OTP: ['', [Validators.required, Validators.minLength(6)]]
    });
    this.resetPasswordForm = this.formBuilder.group({
      password: ['', [Validators.required, Validators.minLength(6)]]
    })
  }

  get f() { return this.forgotPasswordForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.forgotPasswordForm.invalid) {
      return;
    }
    else {
      this.springSecurity.getForgotPassword(this.forgotPasswordForm.get("email").value).subscribe((res) => {
        if (res == null) {
          alert("Sorry This EmailId Does not exits..!!!!");
        }

        else {
          alert("OTP sent to your E-MailId...");
          this.getOTP = true;
          this.user = res as User;
          this.sharedUserService.setUser(this.user);
        }
      });
    }
  }
  get h() { return this.checkOTPForm.controls; }

  onCheckOTP() {
    this.submittedOTP = true;
    // stop here if form is invalid
    if (this.checkOTPForm.invalid) {
      return;
    }
    else {
      this.springSecurity.getOTP(this.user.id,this.checkOTPForm.get("OTP").value).subscribe((res)=>{
        if(res != null){
          console.log(res);
          this.checkOTP=true;
        }else{
          alert("Invalid OTP...!!!");
        }
      });
    }
  }
  get g() { return this.resetPasswordForm.controls; }

  onReset() {
    this.submittedNewPassword = true;
    // stop here if form is invalid
    if (this.resetPasswordForm.invalid) {
      return;
    }
    else {
      this.springSecurity.getResetPassword(this.user.id,this.resetPasswordForm.get("password").value).subscribe((res)=>{
        console.log(res);
        this.route.navigate(['/']);
      });
    }
  }

  onBack() {
    this.route.navigate(['/']);
  }
}
