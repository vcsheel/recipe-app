package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.RecipeCommand;
import com.learn.spring.recipeapp.converters.RecipeCommandToRecipe;
import com.learn.spring.recipeapp.converters.RecipeToRecipeCommand;
import com.learn.spring.recipeapp.models.Recipe;
import com.learn.spring.recipeapp.repositories.RecipeRepository;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit.jupiter.SpringExtension;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;

@ExtendWith(SpringExtension.class)
@SpringBootTest
class RecipeServiceIT {

    private final String DESCRIPTION = "Random description";

    @Autowired
    RecipeService recipeService;

    @Autowired
    RecipeRepository recipeRepository;

    @Autowired
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Autowired
    RecipeCommandToRecipe recipeCommandToRecipe;

    @Transactional
    @Test
    void saveRecipeCommand() {
        //given
        Iterable<Recipe> recipes = recipeRepository.findAll();
        Recipe testRecipe = recipes.iterator().next();
        RecipeCommand testRecipeCommand = recipeToRecipeCommand.convert(testRecipe);

        //when
        testRecipeCommand.setDescription(DESCRIPTION);
        RecipeCommand savedTestRecipe = recipeService.saveRecipeCommand(testRecipeCommand);

        //then
        assertEquals(DESCRIPTION, savedTestRecipe.getDescription());
        assertEquals(testRecipe.getId(), savedTestRecipe.getId());
        assertEquals(testRecipe.getCategories().size(), savedTestRecipe.getCategories().size());
        assertEquals(testRecipe.getIngredients().size(), savedTestRecipe.getIngredients().size());
    }
}