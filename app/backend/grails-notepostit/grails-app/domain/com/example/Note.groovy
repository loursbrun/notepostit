package com.example

class Note {
    String name
    String content
    String urlImg
    static belongsTo  = [ groupPublic: GroupPublic ]
    static constraints = {

    }
}
