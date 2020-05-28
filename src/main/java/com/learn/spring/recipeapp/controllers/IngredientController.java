package com.learn.spring.recipeapp.controllers;

import com.learn.spring.recipeapp.services.IngredientService;
import com.learn.spring.recipeapp.services.RecipeService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
    }

    @GetMapping
    @RequestMapping({"/recipe/{recipeId}/ingredients"})
    public String listIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }


    @GetMapping
    @RequestMapping("/recipe/{recipeId}/ingredient/{ingredientId}/view")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId,
                                 Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId), new Long(ingredientId)));

        return "recipe/ingredient/show";
    }
}
