package com.thevirtugroup.postitnote.model;

import java.time.LocalDateTime;

public class Note {
    private Long Id;
    private String title;
    private String summary;
    private String dateTime;

    public Note(){}

    public Note(Long id, String title, String summary, String dateTime) {
        Id = id;
        this.title = title;
        this.summary = summary;
        this.dateTime = dateTime;
    }

    public Long getId() {
        return Id;
    }

    public void setId(Long id) {
        Id = id;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getSummary() {
        return summary;
    }

    public void setSummary(String summary) {
        this.summary = summary;
    }

    public String getDateTime() {
        return dateTime;
    }

    public void setDateTime(String dateTime) {
        this.dateTime = dateTime;
    }

    @Override
    public String toString() {
        return "Note{" +
                "Id=" + Id +
                ", title='" + title + '\'' +
                ", summary='" + summary + '\'' +
                ", dateTime='" + dateTime + '\'' +
                '}';
    }
}
