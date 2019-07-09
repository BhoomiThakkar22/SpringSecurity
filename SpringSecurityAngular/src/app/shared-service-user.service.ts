import { Injectable } from '@angular/core';
import { User } from './User';

@Injectable({
  providedIn: 'root'
})
export class SharedServiceUserService {
  user:User;

  constructor() { }
  
  getUser(){
    return this.user;
  }
  
  setUser(user:User){
    this.user=user;
  }
}
