package com.example.fabienbrun.notepostit.note;

/**
 * Created by fabienbrun on 08/03/2017.
 */

public class Note  {

    private int id ;
    private String name;
    private String urlImg;
    private String content;
    private int groupID;


    public Note(int id , String name , String content , String urlImg,  int groupID ) {
        this.urlImg = urlImg;
        this.name = name;
        this.id = id;
        this.groupID = groupID;
        this.content = content;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getUrlImg() {
        return urlImg;
    }

    public void setUrlImg(String urlImg) {
        this.urlImg = urlImg;
    }

    public int getGroupID() {
        return groupID;
    }

    public void setGroupID(int groupID) {
        this.groupID = groupID;
    }

    @Override
    public String toString() {
        return "Note{" +
                "content='" + content + '\'' +
                ", id=" + id +
                ", name='" + name + '\'' +
                ", urlImg='" + urlImg + '\'' +
                ", groupID=" + groupID +
                '}';
    }
}
