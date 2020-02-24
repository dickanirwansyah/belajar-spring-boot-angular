import { Component, OnInit } from '@angular/core';
import { TokenStorageService } from '../_services/token.storage.service';
import { AuthService } from '../_services/auth.service';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  form: any = {};
  isLoggedIn = false;
  isLoginFailed = false;
  errorMessage = '';
  roles: string[] = [];

  constructor(private _tokenService:TokenStorageService, 
    private _authService: AuthService) { 
    console.log('CALL LOGIN COMPONENT')
  }

  ngOnInit(){
    if(this._tokenService.getToken()){
      this.isLoggedIn = true;
      this.roles = this._tokenService.getUser().roles;
    }
  }

  onSubmit() {
    this._authService.login(this.form).subscribe(
      data => {
        this._tokenService.saveToken(data.token);
        this._tokenService.saveUser(data);
        this.isLoginFailed = false;
        this.isLoggedIn = true;
        this.roles = this._tokenService.getUser().roles;
        this.reloadPage();
      },
      err => {
        this.errorMessage = err.error.message;
        this.isLoginFailed = true;
      }
    );
  }

  reloadPage(){
    window.location.reload();
  }
}
