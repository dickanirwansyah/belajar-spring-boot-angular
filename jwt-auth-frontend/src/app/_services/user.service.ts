import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL_API = "http://localhost:8888/api/test";
const ALL = "/all";
const MANAGER = "/manager";
const STAFF = "/staff";
const ADMIN = "/admin";

@Injectable({
    providedIn: 'root'
})
export class UserService {

    constructor(private _httpClient: HttpClient){}

    getPublicBoardContent(): Observable<any>{
        return this._httpClient.get(BASE_URL_API + ALL, {responseType: 'text'});
    }

    getAdminBoardContent(): Observable<any>{
        return this._httpClient.get(BASE_URL_API + ADMIN, {responseType: 'text'});
    }

    getManagerBoardContent(): Observable<any> {
        return this._httpClient.get(BASE_URL_API + MANAGER, {responseType: 'text'});
    }

    getStaffBoardContent(): Observable<any> {
        return this._httpClient.get(BASE_URL_API + STAFF, {responseType: 'text'});
    }
}