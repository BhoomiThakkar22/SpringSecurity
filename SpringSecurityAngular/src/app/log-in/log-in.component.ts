import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormBuilder, Validators } from '@angular/forms';
import { SpringSecurityCrudService } from '../spring-security-crud.service';
import { User } from '../User';
import { SharedServiceUserService } from '../shared-service-user.service';

@Component({
  selector: 'app-log-in',
  templateUrl: './log-in.component.html',
  styleUrls: ['./log-in.component.css']
})
export class LogInComponent implements OnInit {
  email: string = '';
  password: string = '';
 
  logInForm: FormGroup;
  submitted = false;
  constructor(private route: Router, private formBuilder: FormBuilder,private springSecurityService:SpringSecurityCrudService,private sharedService:SharedServiceUserService) { }

  ngOnInit() {
    this.logInForm = this.formBuilder.group({
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6)]],
    });
    
  }

  get f() { return this.logInForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.logInForm.invalid) {
      return;
    }
    console.log(this.logInForm.get('email').value);
    console.log(this.logInForm.get('password').value);
    this.springSecurityService.getLogIn(this.logInForm.get('email').value,this.logInForm.get('password').value).subscribe((res)=>{
      if(res == null){
        alert("user is not valid...!!!!");
      }
      else{
        this.sharedService.setUser(res as User);
        this.route.navigate(["/loggedIn"]);
      }
    });
    
  }
  onRegister() {
    this.route.navigate(['\signUp']);
  }
}
