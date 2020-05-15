package com.learn.spring.recipeapp.repositories;

import com.learn.spring.recipeapp.models.Category;
import org.springframework.data.repository.CrudRepository;

public interface CategoryRepository extends CrudRepository<Category, Long> {
}
