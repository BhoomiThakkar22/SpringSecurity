import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { User } from './User';


@Injectable({
  providedIn: 'root'
})
export class SpringSecurityCrudService {

  user:User;
  constructor(private http:HttpClient) { }
  getLogIn(email,password){
    return this.http.post('http://192.168.1.135:8080/user/login',{email,password});
  }

  getRegister(user){
    return this.http.post("http://192.168.1.135:8080/user/registration",user);
  }
  getForgotPassword(email){
    return this.http.get("http://192.168.1.135:8080/user/forgotPassword/"+email);
  }
  getOTP(uuid,otp){
    
    return this.http.post("http://192.168.1.135:8080/user/checkOtp",{uuid,otp});
  }
  getResetPassword(uuid,newPassword){
    return this.http.post("http://192.168.1.135:8080/user/resetPassword",{uuid,newPassword});
  }
  
}
