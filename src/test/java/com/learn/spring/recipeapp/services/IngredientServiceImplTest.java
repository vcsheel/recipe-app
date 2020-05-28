package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.IngredientCommand;
import com.learn.spring.recipeapp.converters.IngredientCommandToIngredient;
import com.learn.spring.recipeapp.converters.IngredientToIngredientCommand;
import com.learn.spring.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.learn.spring.recipeapp.models.Ingredient;
import com.learn.spring.recipeapp.models.Recipe;
import com.learn.spring.recipeapp.repositories.RecipeRepository;
import com.learn.spring.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class IngredientServiceImplTest {

    private final IngredientToIngredientCommand ingredientToIngredientCommand;

    @Mock
    RecipeRepository recipeRepository;

    IngredientService ingredientService;

    UnitOfMeasureRepository uomRepository;

    IngredientCommandToIngredient ingredientCommandToIngredient;

    public IngredientServiceImplTest() {
        MockitoAnnotations.initMocks(this);
        this.ingredientToIngredientCommand = new IngredientToIngredientCommand(new UnitOfMeasureToUnitOfMeasureCommand());
        ingredientService = new IngredientServiceImpl(ingredientToIngredientCommand, ingredientCommandToIngredient, recipeRepository, uomRepository);
    }

    @BeforeEach
    void setUp() {
    }

    @Test
    void findByRecipeIdAndIngredientId() {
        Recipe recipe = new Recipe();
        recipe.setId(1L);

        Ingredient ingredient1 = new Ingredient();
        ingredient1.setId(1L);

        Ingredient ingredient2 = new Ingredient();
        ingredient2.setId(2L);

        recipe.addIngredient(ingredient1);
        recipe.addIngredient(ingredient2);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        IngredientCommand ingredientCommand = ingredientService.findByRecipeIdAndIngredientId(1L, 2L);

        assertNotNull(ingredientCommand);
        verify(recipeRepository, times(1)).findById(anyLong());
        assertEquals(2L, ingredientCommand.getId());
        assertEquals(1L, ingredientCommand.getRecipeId());
    }
}