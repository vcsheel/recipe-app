package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.UnitOfMeasureCommand;
import com.learn.spring.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.learn.spring.recipeapp.repositories.UnitOfMeasureRepository;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.util.HashSet;
import java.util.Set;

@Slf4j
@Service
public class UnitOfMeasureServiceImpl implements UnitOfMeasureService {

    private final UnitOfMeasureRepository uomRepository;
    private final UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand;

    public UnitOfMeasureServiceImpl(UnitOfMeasureRepository uomRepository,
                                    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand) {

        this.uomRepository = uomRepository;
        this.unitOfMeasureToUnitOfMeasureCommand = unitOfMeasureToUnitOfMeasureCommand;
    }

    @Override
    public Set<UnitOfMeasureCommand> listAllUOMs() {
        Set<UnitOfMeasureCommand> uoms = new HashSet<>();
        uomRepository.findAll().forEach(unitOfMeasure -> uoms.add(unitOfMeasureToUnitOfMeasureCommand.convert(unitOfMeasure)));
        return uoms;
    }
}
