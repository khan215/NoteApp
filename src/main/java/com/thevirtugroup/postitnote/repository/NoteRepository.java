package com.thevirtugroup.postitnote.repository;

import com.thevirtugroup.postitnote.Utils.Utils;
import com.thevirtugroup.postitnote.model.Note;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class NoteRepository {

    public List<Note> findAll(){
        return Utils.noteList;
    }

    public Note save(int index, Note note){
        if(note.getId() == -1) {
            note.setId(Utils.noteList.size() + 1L);
            note.setDateTime(Utils.formatDate(note.getDateTime()));
            Utils.noteList.add(note);
            return Utils.noteList.get(Utils.noteList.indexOf(note));
        }else {
            Note note1 = new Note();
            note1.setId(note.getId());
            note1.setSummary(note.getSummary());
            note1.setTitle(note.getTitle());
            note1.setDateTime(Utils.formatDate(note.getDateTime()));
            return Utils.noteList.set(index, note1);
        }
    }

    public void remove(Note note){
        Utils.noteList.remove(note);
    }

}
