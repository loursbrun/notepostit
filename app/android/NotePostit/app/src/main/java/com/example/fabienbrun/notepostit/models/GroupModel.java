package com.example.fabienbrun.notepostit.models;

/**
 * Created by fabienbrun on 07/03/2017.
 */

public class GroupModel {
    private Long id;
    private String title;
    private String note;


    public GroupModel() {
    }

    public GroupModel(String note, String title) {
        this.note = note;
        this.title = title;
    }

    public String getNote() {
        return note;
    }

    public void setNote(String note) {
        this.note = note;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }


    public Long getId() {
        return id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    @Override
    public String toString() {
        return "GroupModel{" +
                "id=" + id +
                ", title='" + title + '\'' +
                ", note='" + note + '\'' +
                '}';
    }
}




