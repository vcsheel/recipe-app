package com.learn.spring.recipeapp.repositories;

import com.learn.spring.recipeapp.models.UnitOfMeasure;
import org.springframework.data.repository.CrudRepository;

public interface UnitOfMeasureRepository extends CrudRepository<UnitOfMeasure, Long> {
}
