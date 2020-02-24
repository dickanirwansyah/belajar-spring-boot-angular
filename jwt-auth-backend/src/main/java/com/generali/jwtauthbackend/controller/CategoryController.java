package com.generali.jwtauthbackend.controller;

import com.generali.jwtauthbackend.entity.Category;
import com.generali.jwtauthbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
@RequestMapping(value = "/api/category")
public class CategoryController {

    @Autowired
    private CategoryService categoryService;

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @GetMapping(value = "/list-category")
    public ResponseEntity<List<Category>> getListCategory(){
        List<Category> categories = categoryService.listCategory();
        return ResponseEntity.ok(categories);
    }
}
