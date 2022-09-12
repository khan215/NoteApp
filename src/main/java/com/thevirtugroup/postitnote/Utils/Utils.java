package com.thevirtugroup.postitnote.Utils;

import com.thevirtugroup.postitnote.model.Note;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import java.lang.reflect.Array;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;
import java.util.List;

public class Utils {
    public static List<Note> noteList =new ArrayList<Note>(){{
        add( new Note(1L, "Angular JS","Angular JS is a web ui framework..", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
        add(new Note(2L, "Java Backend","Java is best server side language..", LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"))));
    }};

    public static Note getNoteById(Long id){
        return noteList.stream().filter(x -> x.getId() == id).findAny().get();
    }

    public static String formatDate(String dateTimeString){
        try {
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd'T'HH:mm:ss.SSSX");
            LocalDateTime localDateTime = LocalDateTime.parse(dateTimeString, formatter);
            return localDateTime.format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        }catch (Exception ex){
            ex.toString();
            return null;
        }
    }
}