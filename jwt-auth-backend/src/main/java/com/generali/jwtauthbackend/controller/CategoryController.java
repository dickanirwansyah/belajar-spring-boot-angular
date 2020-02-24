package com.generali.jwtauthbackend.controller;

import com.generali.jwtauthbackend.entity.Category;
import com.generali.jwtauthbackend.payload.RequestCategory;
import com.generali.jwtauthbackend.service.CategoryService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
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

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/save-category")
    public ResponseEntity<Category> saveCategory(@RequestBody @Valid RequestCategory category){
        Category saveCategory = categoryService.createCategory(category);
        return ResponseEntity.ok(saveCategory);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/update-category/{id}")
    public ResponseEntity<Category> updateCategory(@RequestBody @Valid RequestCategory category,
                                                   @PathVariable("id")int id){
        Category updateCategory = categoryService.updateCategory(category, id);
        return ResponseEntity.ok(updateCategory);
    }

    @PreAuthorize("hasRole('ROLE_ADMIN') or hasRole('ROLE_MANAGER')")
    @PostMapping(value = "/delete-category/{id}")
    public ResponseEntity<Void> deleteCategory(@PathVariable("id")int id){
        categoryService.deleteCategory(id);
        return ResponseEntity.ok().build();
    }
}
