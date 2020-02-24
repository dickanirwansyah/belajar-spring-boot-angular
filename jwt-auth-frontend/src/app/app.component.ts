import { Component } from '@angular/core';
import { TokenStorageService } from './_services/token.storage.service';

const LEVEL_ADMIN = "ROLE_ADMIN";
const LEVEL_MANAGER = "ROLE_MANAGER";
const LEVEL_STAFF = "ROLE_STAFF";

@Component({
  selector: 'app-root',
  templateUrl: './app.component.html',
  styleUrls: ['./app.component.css']
})
export class AppComponent {
  
  private roles: string[];
  isLoggedIn = false;
  showAdminBoard = false;
  showManagerBoard = false;
  showStaffBoard = false;
  username: string;

  constructor(private _tokenService: TokenStorageService){
    this.isLoggedIn = !!this._tokenService.getToken();
    if (this.isLoggedIn){
      const user = this._tokenService.getUser();
      this.roles = user.roles;
      
      this.showAdminBoard = this.roles.includes(LEVEL_ADMIN);
      this.showManagerBoard = this.roles.includes(LEVEL_MANAGER);
      this.showStaffBoard = this.roles.includes(LEVEL_STAFF);
      this.username = user.username;
    }
  }

  logout(){
    this._tokenService.signOut();
    window.location.href="/login";
  }
}
