package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.Utils.Utils;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.junit.jupiter.api.Assertions;

import java.util.List;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertNotEquals;

//@RunWith(SpringJUnit4ClassRunner.class)
//@SpringApplicationConfiguration(classes = Application.class)
class NoteControllerTest {

//    @Autowired
    private NoteRepository noteRepository;

    @org.junit.jupiter.api.Test
    void getAll() {
        List<Note> result = noteRepository.findAll();

        Assertions.assertEquals(result.size(),2);

    }

    @org.junit.jupiter.api.Test
    void createNote() {

        Note note = noteRepository.save(0,
                new Note(-1L,"Test","Test","2022-09-12")
        );

        assertNotEquals(note.getId(),null);

    }

    @org.junit.jupiter.api.Test
    void updateEmployee() {
        Note testNote = new Note(2L,"Test","Test","2022-09-12");
        Note note = noteRepository.save(Utils.noteList.indexOf(testNote),
                new Note(2L,"Test","Test","2022-09-12")
        );

        assertEquals(note,testNote);
    }

    @org.junit.jupiter.api.Test
    void deleteEmployee() {
        int listSize = noteRepository.findAll().size();
        Note testNote = new Note(2L,"Test","Test","2022-09-12");
        noteRepository.remove(testNote);

        assertEquals(listSize, listSize - 1);

    }
}