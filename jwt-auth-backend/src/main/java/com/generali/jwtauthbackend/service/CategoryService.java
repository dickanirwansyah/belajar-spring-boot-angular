package com.generali.jwtauthbackend.service;

import com.generali.jwtauthbackend.entity.Category;
import com.generali.jwtauthbackend.repository.CategoryRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class CategoryService {

    @Autowired
    private CategoryRepository categoryRepository;

    public List<Category> listCategory(){
        return categoryRepository.findAll();
    }
}
