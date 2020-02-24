package com.generali.jwtauthbackend.service;

import com.generali.jwtauthbackend.entity.Category;
import com.generali.jwtauthbackend.exception.ResourceConflictException;
import com.generali.jwtauthbackend.exception.ResourceNotFoundException;
import com.generali.jwtauthbackend.payload.RequestCategory;
import com.generali.jwtauthbackend.repository.CategoryRepository;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Date;
import java.util.List;

@Service
public class CategoryService {

    private static final Logger log = LoggerFactory.getLogger(CategoryService.class);

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }

    public Category createCategory(RequestCategory category){

        if (categoryRepository.findCategoryByName(category.getName()).isPresent()){
            throw new ResourceConflictException("category with name "+category.getName()+" is already taken");
        }

        Category entityCategory = Category.builder()
                .name(category.getName())
                .createdAt(new Date())
                .updatedAt(new Date())
                .build();
        log.info("#SAVE CATEGORY -> "+entityCategory.toString());
        return categoryRepository.save(entityCategory);
    }

    public Category updateCategory(RequestCategory category, int categoryId){

        Category entityCategory = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category id is not found"));

        entityCategory.setName(category.getName());
        entityCategory.setCreatedAt(entityCategory.getCreatedAt());
        entityCategory.setUpdatedAt(new Date());
        categoryRepository.save(entityCategory);

        log.info("#UPDATE CATEGORY -> "+entityCategory.toString());
        return entityCategory;
    }

    public void deleteCategory(int categoryId){
        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new ResourceNotFoundException("category id is not found"));
        log.info("#DELETE CATEGORY WITH ID -> "+category.getId());
        categoryRepository.delete(category);
    }
}
