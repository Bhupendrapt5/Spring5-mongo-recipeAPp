package io.bhupendra.converters;

import io.bhupendra.commands.NotesCommand;
import io.bhupendra.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesToNotesCommandTest {

    public static String ID = "1L";
    public static String recipeNotes = "This is notes on recipe";

    NotesToNotesCommand converter;

    @BeforeEach
    void setUp() {
        converter = new NotesToNotesCommand();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new Notes()));
    }

    @Test
    void convert() {

        //given
        Notes notes = new Notes();
        notes.setId(ID);
        notes.setRecipeNotes(recipeNotes);

        //when
        NotesCommand command = converter.convert(notes);

        //then
        assertNotNull(command);
        assertEquals(ID, command.getId());
        assertEquals(recipeNotes, command.getRecipeNotes());
    }
}