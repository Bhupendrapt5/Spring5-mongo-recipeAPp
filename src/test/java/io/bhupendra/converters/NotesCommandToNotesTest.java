package io.bhupendra.converters;

import io.bhupendra.commands.NotesCommand;
import io.bhupendra.domain.Notes;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.*;

class NotesCommandToNotesTest {

    public static String ID = "1L";
    public static String recipeNotes = "This is notes on recipe";

    NotesCommandToNotes converter;

    @BeforeEach
    void setUp() {
        converter = new NotesCommandToNotes();
    }

    @Test
    void testNullObject() {
        assertNull(converter.convert(null));
    }

    @Test
    void testEmptyObject() {
        assertNotNull(converter.convert(new NotesCommand()));
    }

    @Test
    void convert() {

        //given
        NotesCommand command = new NotesCommand();
        command.setId(ID);
        command.setRecipeNotes(recipeNotes);

        //when
        Notes notes = converter.convert(command);

        //then
        assertNotNull(notes);
        assertEquals(ID, notes.getId());
        assertEquals(recipeNotes, notes.getRecipeNotes());
    }
}