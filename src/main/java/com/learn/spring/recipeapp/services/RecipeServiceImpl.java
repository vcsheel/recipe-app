package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.RecipeCommand;
import com.learn.spring.recipeapp.converters.RecipeCommandToRecipe;
import com.learn.spring.recipeapp.converters.RecipeToRecipeCommand;
import com.learn.spring.recipeapp.models.Recipe;
import com.learn.spring.recipeapp.repositories.RecipeRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

@Slf4j
@Service
public class RecipeServiceImpl implements RecipeService {

    private final RecipeRepository recipeRepository;
    private final RecipeToRecipeCommand recipeToRecipeCommand;
    private final RecipeCommandToRecipe recipeCommandToRecipe;

    public RecipeServiceImpl(RecipeRepository recipeRepository, RecipeToRecipeCommand recipeToRecipeCommand,
                             RecipeCommandToRecipe recipeCommandToRecipe) {
        this.recipeRepository = recipeRepository;
        this.recipeToRecipeCommand = recipeToRecipeCommand;
        this.recipeCommandToRecipe = recipeCommandToRecipe;
    }

    @Override
    public Set<Recipe> getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        recipeRepository.findAll().forEach(recipes::add);
        //log.debug(recipes.toString());
        return recipes;
    }

    @Override
    public Recipe findById(Long id) {
        Optional<Recipe> recipe = recipeRepository.findById(id);
        if(!recipe.isPresent()) {
            throw new RuntimeException("Recipe not found");
        }
        return recipe.get();
    }

    @Override
    public RecipeCommand saveRecipeCommand(RecipeCommand recipeCommand) {

        Recipe detachedRecipe = recipeCommandToRecipe.convert(recipeCommand);

        Recipe savedRecipe = recipeRepository.save(detachedRecipe);
        log.debug("Saved Recipe command id: " + savedRecipe.getId());
        return recipeToRecipeCommand.convert(savedRecipe);
    }
}
