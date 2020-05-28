package com.learn.spring.recipeapp.services;

import com.learn.spring.recipeapp.commands.UnitOfMeasureCommand;
import com.learn.spring.recipeapp.converters.UnitOfMeasureToUnitOfMeasureCommand;
import com.learn.spring.recipeapp.models.UnitOfMeasure;
import com.learn.spring.recipeapp.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

class UnitOfMeasureServiceImplTest {

    UnitOfMeasureService service;

    @Mock
    UnitOfMeasureRepository repository;

    UnitOfMeasureToUnitOfMeasureCommand unitOfMeasureToUnitOfMeasureCommand = new UnitOfMeasureToUnitOfMeasureCommand();

    @BeforeEach
    void setUp() {
        MockitoAnnotations.initMocks(this);

        service = new UnitOfMeasureServiceImpl(repository, unitOfMeasureToUnitOfMeasureCommand);
    }

    @Test
    void listAllUOMs() {

        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId(1L);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId(2L);

        Set<UnitOfMeasure> uoms = new HashSet<>();
        uoms.add(uom1);
        uoms.add(uom2);
        System.out.println(uoms.size()+" size");
        when(repository.findAll()).thenReturn(uoms);

        Set<UnitOfMeasureCommand> uomCommands = service.listAllUOMs();
        assertNotNull(uomCommands);
        assertEquals(2, uomCommands.size());
        verify(repository, times(1)).findAll();
    }
}