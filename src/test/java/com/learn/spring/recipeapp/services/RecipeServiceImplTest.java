package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.RecipeCommand;
import com.learn.spring.recipeapp.converters.RecipeCommandToRecipe;
import com.learn.spring.recipeapp.converters.RecipeToRecipeCommand;
import com.learn.spring.recipeapp.exceptions.NotFoundException;
import com.learn.spring.recipeapp.models.Recipe;
import com.learn.spring.recipeapp.repositories.RecipeRepository;
import org.aspectj.weaver.ast.Not;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;

import java.util.HashSet;
import java.util.Optional;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.*;

class RecipeServiceImplTest {

    @Mock
    RecipeRepository recipeRepository;

    @Mock
    RecipeToRecipeCommand recipeToRecipeCommand;

    @Mock
    RecipeCommandToRecipe recipeCommandToRecipe;

    @InjectMocks
    RecipeServiceImpl recipeService;

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);
    }

    @Test
    void getAllRecipes() {
        Set<Recipe> recipes = new HashSet<>();
        Recipe recipe = new Recipe();
        recipes.add(recipe);

        when(recipeService.getAllRecipes()).thenReturn(recipes);

        Set<Recipe> returnedRecipes = recipeService.getAllRecipes();

        assertEquals(1, returnedRecipes.size());
        verify(recipeRepository, times(1)).findAll();
    }

    @Test
    void findById() {
        Recipe recipe = new Recipe();
        final Long id = 1L;
        recipe.setId(id);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        Recipe returnedRecipe = recipeService.findById(id);

        assertNotNull(returnedRecipe);
        assertEquals(id, returnedRecipe.getId());
        verify(recipeRepository, never()).findAll();
        verify(recipeRepository, times(1)).findById(anyLong());
    }

    @Test
    void findRecipeCommandById() {
        Recipe recipe = new Recipe();
        final Long id = 1L;
        recipe.setId(id);

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.of(recipe));

        RecipeCommand recipeCommand = new RecipeCommand();
        recipeCommand.setId(id);

        when(recipeToRecipeCommand.convert(any())).thenReturn(recipeCommand);

        RecipeCommand returnedRecipe = recipeService.findCommandById(id);

        assertNotNull(returnedRecipe);

        verify(recipeRepository, times(1)).findById(anyLong());
        verify(recipeRepository, never()).findAll();
    }

    @Test
    void recipeDeleteById() {
        final Long id = 1L;
        recipeService.deleteById(id);

        verify(recipeRepository, times(1)).deleteById(anyLong());
    }

    @Test
    void recipeNotFoundById () {

        when(recipeRepository.findById(anyLong())).thenReturn(Optional.empty());
        assertThrows(NotFoundException.class, ()->recipeService.findById(1L));
    }
}