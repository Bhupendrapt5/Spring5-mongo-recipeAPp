package io.bhupendra.repositories;

import io.bhupendra.domain.UnitOfMeasure;
import jdk.nashorn.internal.ir.annotations.Ignore;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Disabled;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.Optional;

import static org.junit.jupiter.api.Assertions.assertEquals;

@Disabled
@ExtendWith(MockitoExtension.class)
public class UnitOfMeasureRepositoryIT {

    @Autowired
    UnitOfMeasureRepository unitOfMeasureRepository;

    @BeforeEach
    public void setUp() throws Exception {

    }

    @Test
    public void findByDescription() {
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Pinch");

        assertEquals("Pinch",
                unitOfMeasure.get().getDescription());
    }
    @Test
    public void findByDescriptionCup() {
        Optional<UnitOfMeasure> unitOfMeasure = unitOfMeasureRepository.findByDescription("Cup");

//        assertNull(unitOfMeasure);
        assertEquals("Cup",
                unitOfMeasure.get().getDescription());
    }
}