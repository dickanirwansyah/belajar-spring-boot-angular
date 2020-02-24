import { Injectable } from '@angular/core';
import { HttpClient } from '@angular/common/http';
import { Observable } from 'rxjs';

const BASE_URL_API = "http://localhost:8888/api/category/";
const LIST_CATEGORY = "list-category";

@Injectable({
    providedIn: 'root'
})
export class CategoryService {

    constructor(private _httpClient: HttpClient){}

    getListCategory():Observable<any>{
        return this._httpClient.get(BASE_URL_API + LIST_CATEGORY)
    }
}