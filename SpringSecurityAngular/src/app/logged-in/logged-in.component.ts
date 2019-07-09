import { Component, OnInit } from '@angular/core';
import { SharedServiceUserService } from '../shared-service-user.service';
import { User } from '../User';

@Component({
  selector: 'app-logged-in',
  templateUrl: './logged-in.component.html',
  styleUrls: ['./logged-in.component.css']
})
export class LoggedInComponent implements OnInit {
  user:User;
  constructor(private sharedService:SharedServiceUserService) {this.user=this.sharedService.getUser(); }

  ngOnInit() {
    // this.user=this.sharedService.getUser();
    // console.log(this.user);
  }

}
