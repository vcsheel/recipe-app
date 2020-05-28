package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.UnitOfMeasureCommand;

import java.util.Set;

public interface UnitOfMeasureService {
    Set<UnitOfMeasureCommand> listAllUOMs();
}
