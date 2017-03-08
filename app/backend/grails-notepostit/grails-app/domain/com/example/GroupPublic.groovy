package com.example

class GroupPublic {
    String name
    Boolean isPublic
    List<String> emails = new ArrayList<String>()
    static hasMany = [ notes: Note ]
    static constraints = {
    }
}
