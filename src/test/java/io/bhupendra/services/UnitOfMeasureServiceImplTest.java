package io.bhupendra.services;

import io.bhupendra.commands.UnitOfMeasureCommand;
import io.bhupendra.converters.UnitOfMeasureToUnitOfMeasureCommand;
import io.bhupendra.domain.UnitOfMeasure;
import io.bhupendra.repositories.UnitOfMeasureRepository;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import java.util.HashSet;
import java.util.Set;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.*;

@ExtendWith(MockitoExtension.class)
class UnitOfMeasureServiceImplTest {

    @Mock
    UnitOfMeasureRepository unitOfMeasureRepository;

    UnitOfMeasureToUnitOfMeasureCommand measureCommand
                = new UnitOfMeasureToUnitOfMeasureCommand();

    @InjectMocks
    UnitOfMeasureServiceImpl unitOfMeasuresService;


    @BeforeEach
    void setUp() {

        unitOfMeasuresService = new UnitOfMeasureServiceImpl(unitOfMeasureRepository, measureCommand);
    }

    @Test
    void listAllUOMs() {
        //given
        Set<UnitOfMeasure> unitOfMeasures = new HashSet<>();
        UnitOfMeasure uom1 = new UnitOfMeasure();
        uom1.setId("1L");
        unitOfMeasures.add(uom1);

        UnitOfMeasure uom2 = new UnitOfMeasure();
        uom2.setId("2L");
        unitOfMeasures.add(uom2);

        //when
        when(unitOfMeasureRepository.findAll()).thenReturn(unitOfMeasures);
        Set<UnitOfMeasureCommand> commands = unitOfMeasuresService.listAllUoms();

        //then
        assertEquals(2, commands.size());
        verify(unitOfMeasureRepository, times(1)).findAll();
    }
}