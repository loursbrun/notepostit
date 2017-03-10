package com.example.fabienbrun.notepostit.group;




import com.example.fabienbrun.notepostit.note.Note;

import java.util.ArrayList;

/**
 * Created by fabienbrun on 08/03/2017.
 */

public class GroupPublic {
    private String name;
    private ArrayList<String> emails = new ArrayList<>();
    private ArrayList<Note> notes = new ArrayList<>();


    public GroupPublic(String name) {
        this.emails = new ArrayList();
        this.name = name;
        this.notes = notes;
    }



    public ArrayList<String> getEmails() {
        return emails;
    }


    public void addEmail(String email) {
        this.emails.add(email);
    }

    public void setNotes(ArrayList<Note> notes) {
        this.notes = notes;
    }

    public void addNote(Note note) {
        this.notes.add(note);
    }


    public String getName() {
        return name;
    }

    public void setName(String nom) {
        this.name = name;
    }


    public ArrayList<Note> getNoteListe() {
        return notes;
    }


    @Override
    public String toString() {
        return "GroupPublic{" +
                "emails=" + emails +
                ", name='" + name + '\'' +
                ", notes=" + notes +
                '}';
    }
}

