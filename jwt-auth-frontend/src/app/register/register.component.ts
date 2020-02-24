import { Component, OnInit } from '@angular/core';
import { AuthService } from '../_services/auth.service';


@Component({
    selector: 'app-register',
    templateUrl: './register.component.html',
    styleUrls: ['./register.component.css']
})
export class RegisterComponent implements OnInit{

    form:any = {};
    isSuccessful = false;
    isSingUpFailed = false;
    errorMessage = '';

    constructor(private _authService:AuthService){}

    ngOnInit(){}

    onSubmit(){
        this._authService.register(this.form).subscribe(
            data => {
        
                console.log(data);
                this.isSuccessful = true;
                this.isSingUpFailed = false;
            },
            err => {
                /** erro by conflict code */
                if(err.error.code === 409){
                    console.log(err.error.message);
                    this.errorMessage = err.error.message;
                    this.isSingUpFailed = true;
                }else{

                /**default error message */        
                this.errorMessage = err.error.message;
                this.isSingUpFailed = true;
            }
            }
        )
    }
}