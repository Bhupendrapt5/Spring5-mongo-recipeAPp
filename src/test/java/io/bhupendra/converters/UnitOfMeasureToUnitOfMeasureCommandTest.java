package io.bhupendra.converters;

import io.bhupendra.commands.UnitOfMeasureCommand;
import io.bhupendra.domain.UnitOfMeasure;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class UnitOfMeasureToUnitOfMeasureCommandTest {

    public static String ID = "1L";
    public static String description = "description";

    UnitOfMeasureToUnitOfMeasureCommand converter;

    @BeforeEach
    void setUp() {
        converter = new UnitOfMeasureToUnitOfMeasureCommand();
    }

    @Test
    void withNullObject() {
        assertNull(converter.convert(null));
    }
    @Test
    void withEmptyObject() {
        assertNotNull(converter.convert(new UnitOfMeasure()));
    }

    @Test
    void convert() {
        //given
        UnitOfMeasure uom = new UnitOfMeasure();
        uom.setId(ID);
        uom.setDescription(description);

        //when
        UnitOfMeasureCommand uomCommand = converter.convert(uom);

        //then
        assertNotNull(uomCommand);
        assertEquals(ID, uomCommand.getId());
        assertEquals(description, uomCommand.getDescription());
    }
}