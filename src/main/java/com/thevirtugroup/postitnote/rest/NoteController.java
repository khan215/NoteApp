package com.thevirtugroup.postitnote.rest;

import com.thevirtugroup.postitnote.Utils.Utils;
import com.thevirtugroup.postitnote.model.Note;
import com.thevirtugroup.postitnote.repository.NoteRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class NoteController
{
    @Autowired
    private NoteRepository noteRepository;

    @RequestMapping(value = "notes", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.GET)
    public ResponseEntity<List<Note>> getAll() throws Exception {
        return new ResponseEntity<>(noteRepository.findAll(), HttpStatus.OK);
    }

    @RequestMapping(value = "createNote", produces = MediaType.APPLICATION_JSON_VALUE,  method = RequestMethod.POST)
    public ResponseEntity<Note> createNote(@RequestBody Note note) throws Exception {
        return new ResponseEntity<>(noteRepository.save(-1, note), HttpStatus.CREATED);
    }

    @RequestMapping(value = "updateNote/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, method = RequestMethod.PUT)
    public ResponseEntity<Note> updateEmployee(@PathVariable("id") Long id, @RequestBody Note note) throws Exception
    {
        Note note1 = Utils.getNoteById(id);
        if(note1 != null){
            return new ResponseEntity<Note>(noteRepository.save(Utils.noteList.indexOf(note1), note), HttpStatus.OK);
        }
        return new ResponseEntity<Note>(HttpStatus.NOT_FOUND);
    }

    @RequestMapping(value = "/deleteNote/{id}", method = RequestMethod.DELETE)
    public ResponseEntity<String> deleteEmployee(@PathVariable("id") Long id) throws Exception
    {
        Note note1 = Utils.getNoteById(id);
        if(note1 != null){
            Utils.noteList.remove(note1);
            return new ResponseEntity<String>(HttpStatus.OK);
        }
        return new ResponseEntity<String>(HttpStatus.NOT_FOUND);
    }
}
