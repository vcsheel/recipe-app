package com.learn.spring.recipeapp.repositories;

import com.learn.spring.recipeapp.models.Recipe;
import org.springframework.data.repository.CrudRepository;

public interface RecipeRepository extends CrudRepository<Recipe, Long> {
}
