import { Component, OnInit } from '@angular/core';

import { SpringSecurityCrudService } from '../spring-security-crud.service';
import { FormBuilder, FormGroup, Validators } from '@angular/forms';
import { User } from '../User';
import { Router } from '@angular/router';

@Component({
  selector: 'app-sign-up',
  templateUrl: './sign-up.component.html',
  styleUrls: ['./sign-up.component.css']
})
export class SignUpComponent implements OnInit {
  firstName: string = '';
  lastName: string = '';
  email: string = '';
  roleId: number;
  password: string = '';
  tempUser: User = new User('', '', '', '', '');
  registerForm: FormGroup;
  submitted = false;
  constructor(private springsecurity: SpringSecurityCrudService, private formBuilder: FormBuilder,private route:Router) { }

  ngOnInit() {
    this.registerForm = this.formBuilder.group({
      firstName: ['', Validators.required],
      lastName: ['', Validators.required],
      email: ['', [Validators.required, Validators.email]],
      password: ['', [Validators.required, Validators.minLength(6), Validators.maxLength(10)]],
      confirmPassword: ['', Validators.required]
    });
  }
  get f() { return this.registerForm.controls; }

  onSubmit() {
    this.submitted = true;
    // stop here if form is invalid
    if (this.registerForm.invalid) {
      return;
    }
    else {

      this.tempUser.firstName = this.registerForm.get('firstName').value;
      this.tempUser.lastName = this.registerForm.get('lastName').value;
      this.tempUser.emailId = this.registerForm.get('email').value;
      this.tempUser.password = this.registerForm.get('password').value;
      console.log(this.tempUser.firstName + '' + this.tempUser.lastName);
      console.log(this.tempUser.emailId);
      console.log(this.tempUser.password);
      this.springsecurity.getRegister(this.tempUser).subscribe((res)=>{
       console.log(res);
       this.route.navigate(['/login']);
      });
    }
  }
  onBack(){
    this.route.navigate(['/']);
  }
}
