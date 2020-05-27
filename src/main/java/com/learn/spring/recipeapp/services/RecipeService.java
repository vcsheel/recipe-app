package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.RecipeCommand;
import com.learn.spring.recipeapp.models.Recipe;

import java.util.Set;

public interface RecipeService {

    Set<Recipe> getAllRecipes();

    Recipe findById(Long id);

    RecipeCommand findCommandById(Long id);

    RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand);
}
