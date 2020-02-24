package com.generali.jwtauthbackend.repository;

import com.generali.jwtauthbackend.entity.Category;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CategoryRepository extends JpaRepository<Category, Integer> {
}
