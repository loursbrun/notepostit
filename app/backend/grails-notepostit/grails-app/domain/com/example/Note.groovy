package com.example

class Note {
    String name
    String content
    String urlImg
    int groupID
    static belongsTo  = [ groupPublic: GroupPublic ]
    static constraints = {

    }
}
