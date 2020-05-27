package com.learn.spring.recipeapp.commands;

import com.learn.spring.recipeapp.constants.Difficulty;
import com.learn.spring.recipeapp.models.Notes;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@NoArgsConstructor
public class RecipeCommand {
    private Long id;
    private String description;
    private Integer prepTime;
    private Integer cookTime;
    private String source;
    private String url;
    private Integer servings;
    private String directions;
    private Difficulty difficulty;
    private Byte[] image;
    private NotesCommand notes;
    private Set<IngredientCommand> ingredients = new HashSet<>();
    private Set<CategoryCommand> categories = new HashSet<>();
}
