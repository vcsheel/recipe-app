package com.learn.spring.recipeapp.converters;

import com.learn.spring.recipeapp.commands.RecipeCommand;
import com.learn.spring.recipeapp.models.Recipe;
import lombok.Synchronized;
import org.springframework.core.convert.converter.Converter;
import org.springframework.lang.Nullable;
import org.springframework.stereotype.Component;

@Component
public class RecipeCommandToRecipe implements Converter<RecipeCommand, Recipe> {

    private final NotesCommandToNotes notesConverter;
    private final IngredientCommandToIngredient ingredientConverter;
    private final CategoryCommandToCategory categoryConverter;

    public RecipeCommandToRecipe(NotesCommandToNotes notesConverter,
                                 IngredientCommandToIngredient ingredientConverter,
                                 CategoryCommandToCategory categoryConverter) {

        this.notesConverter = notesConverter;
        this.ingredientConverter = ingredientConverter;
        this.categoryConverter = categoryConverter;
    }


    @Synchronized
    @Nullable
    @Override
    public Recipe convert(RecipeCommand source) {

        if(source == null) {
            return null;
        }

        final Recipe recipe = new Recipe();
        recipe.setId(source.getId());
        recipe.setCookTime(source.getCookTime());
        recipe.setPrepTime(source.getPrepTime());
        recipe.setServings(source.getServings());
        recipe.setDescription(source.getDescription());
        recipe.setDirections(source.getDirections());
        recipe.setDifficulty(source.getDifficulty());
        recipe.setSource(source.getSource());
        recipe.setUrl(source.getUrl());

        recipe.setNotes(notesConverter.convert(source.getNotes()));

        if(recipe.getCategories() != null && recipe.getCategories().size() > 0) {
            source.getCategories()
                    .forEach(categoryCommand -> recipe.getCategories().add(categoryConverter.convert(categoryCommand)));
        }

        if(recipe.getIngredients() != null && recipe.getIngredients().size() > 0) {
            source.getIngredients()
                    .forEach(ingredientCommand -> recipe.getIngredients().add(ingredientConverter.convert(ingredientCommand)));
        }

        return recipe;
    }
}
