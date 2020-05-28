package com.learn.spring.recipeapp.controllers;

import com.learn.spring.recipeapp.commands.IngredientCommand;
import com.learn.spring.recipeapp.commands.RecipeCommand;
import com.learn.spring.recipeapp.commands.UnitOfMeasureCommand;
import com.learn.spring.recipeapp.models.Ingredient;
import com.learn.spring.recipeapp.services.IngredientService;
import com.learn.spring.recipeapp.services.RecipeService;
import com.learn.spring.recipeapp.services.UnitOfMeasureService;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

@Controller
public class IngredientController {

    private final RecipeService recipeService;
    private final IngredientService ingredientService;
    private final UnitOfMeasureService unitOfMeasureService;

    public IngredientController(RecipeService recipeService, IngredientService ingredientService, UnitOfMeasureService unitOfMeasureService) {
        this.recipeService = recipeService;
        this.ingredientService = ingredientService;
        this.unitOfMeasureService = unitOfMeasureService;
    }

    @GetMapping({"/recipe/{recipeId}/ingredients"})
    public String listIngredients(@PathVariable String recipeId, Model model) {
        model.addAttribute("recipe", recipeService.findCommandById(new Long(recipeId)));
        return "recipe/ingredient/list";
    }


    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/view")
    public String showIngredient(@PathVariable String recipeId, @PathVariable String ingredientId,
                                 Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId), new Long(ingredientId)));

        return "recipe/ingredient/show";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/update")
    public String updateIngredient(@PathVariable String recipeId, @PathVariable String ingredientId, Model model) {

        model.addAttribute("ingredient", ingredientService.findByRecipeIdAndIngredientId(new Long(recipeId), new Long(ingredientId)));

        model.addAttribute("uomList", unitOfMeasureService.listAllUOMs());
        return "recipe/ingredient/ingredientform";
    }

    @PostMapping("/recipe/{recipeId}/ingredient")
    public String savedIngredient(@ModelAttribute IngredientCommand command) {

        IngredientCommand savedIngredient = ingredientService.saveIngredientCommand(command);

        return "redirect:/recipe/" + savedIngredient.getRecipeId() + "/ingredient/" +savedIngredient.getId() + "/view";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/new")
    public String newIngredient(@PathVariable String recipeId, Model model) {

        RecipeCommand recipeCommand = recipeService.findCommandById(Long.valueOf(recipeId));
        //todo exaction handling for bad id

        IngredientCommand ingredientCommand = new IngredientCommand();
        ingredientCommand.setRecipeId(Long.valueOf(recipeId));

        model.addAttribute("ingredient", ingredientCommand);

        //init uom
        ingredientCommand.setUom(new UnitOfMeasureCommand());

        model.addAttribute("uomList", unitOfMeasureService.listAllUOMs());
        return "recipe/ingredient/ingredientform";
    }

    @GetMapping("/recipe/{recipeId}/ingredient/{ingredientId}/delete")
    public String deleteIngredient(@PathVariable String recipeId, @PathVariable String ingredientId,
                                   Model model) {

        ingredientService.deleteIngredient(Long.valueOf(recipeId), Long.valueOf(ingredientId));

        return "redirect:/recipe/" + recipeId + "/ingredients";
    }
}
