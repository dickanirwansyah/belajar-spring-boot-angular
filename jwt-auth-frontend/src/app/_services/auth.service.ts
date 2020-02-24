import { Injectable } from '@angular/core';
import { HttpClient, HttpHeaders} from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL_API = "http://localhost:8888/api/auth/";
const httpOptions = {
    headers: new HttpHeaders({ 'Content-Type': 'application/json'})
}

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private _httpClient: HttpClient){}

    login(credentials): Observable<any>{
        return this._httpClient.post(BASE_URL_API + 'login', {
            username : credentials.username,
            password: credentials.password
        }, httpOptions);
    }

    register(user) : Observable<any>{
        return this._httpClient.post(BASE_URL_API + 'register', {
            name: user.name,
            username: user.username,
            password: user.password,
            email: user.email
        },httpOptions)
    }
}