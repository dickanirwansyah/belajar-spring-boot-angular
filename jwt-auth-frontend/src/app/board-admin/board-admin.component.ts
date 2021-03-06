import { Component, OnInit } from '@angular/core';
import { UserService } from '../_services/user.service';

@Component({
  selector: 'app-board-admin',
  templateUrl: './board-admin.component.html',
  styleUrls: ['./board-admin.component.css']
})
export class BoardAdminComponent implements OnInit {

  content = '';
  constructor(private _userService:UserService) { }

  ngOnInit() {
    this._userService.getAdminBoardContent().subscribe(
      data => {
        this.content = data
      },
      err => {
        this.content = JSON.parse(err.error).message;
      }
    )
  }

}
