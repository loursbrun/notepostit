package com.example.fabienbrun.notepostit.models;

/**
 * Created by fabienbrun on 07/03/2017.
 */

public class GroupModel {
    private Long id;
    private String title;

    public GroupModel() {
    }

    public GroupModel(String movie) {
        title = movie;
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
        return "Movie{" +
                "id=" + id +
                ", title='" + title + '\'' +
                '}';
    }
}




