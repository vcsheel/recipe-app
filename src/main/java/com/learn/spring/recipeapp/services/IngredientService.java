package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.IngredientCommand;

public interface IngredientService {

    IngredientCommand findByRecipeIdAndIngredientId(Long recipeId, Long ingredientId);

    IngredientCommand saveIngredientCommand(IngredientCommand command);

    void deleteIngredient(Long recipeId, Long ingredientId);
}
