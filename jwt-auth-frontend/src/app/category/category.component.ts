import { Component, OnInit } from '@angular/core';
import { CategoryService } from '../_services/category.service';
import { Observable } from 'rxjs';
import { Category } from './category';

@Component({
  selector: 'app-category',
  templateUrl: './category.component.html',
  styleUrls: ['./category.component.css']
})
export class CategoryComponent implements OnInit {

  categorys: Observable<Category[]>;

  constructor(private _categoryService:CategoryService) {}

  ngOnInit() {
    this.getComponentCategoryList();
  }

  getComponentCategoryList(){
    this.categorys = this._categoryService.getListCategory();
  }
}
