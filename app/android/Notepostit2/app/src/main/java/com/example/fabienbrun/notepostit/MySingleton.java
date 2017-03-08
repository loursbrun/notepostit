package com.example.fabienbrun.notepostit;

import com.example.fabienbrun.notepostit.group.GroupPublic;
import com.example.fabienbrun.notepostit.note.Note;
import com.example.fabienbrun.notepostit.user.User;

import java.util.ArrayList;

/**
 * Created by fabienbrun on 08/03/2017.
 */

public class MySingleton {
    private static MySingleton mySingleton;

    public ArrayList<GroupPublic> groupsList = new ArrayList<>();
    public ArrayList<User> usersList = new ArrayList<>();


    private MySingleton() {







        // Add Grpous & Notes
        Note note1 = new Note(1,"nomememe","www.google.fr","ghjhghjhgj",1);
        Note note2 = new Note(1,"nomememe","www.google.fr","ghjhghjhgj",2);
        Note note3 = new Note(1,"nomememe","www.google.fr","ghjhghjhgj",3);

        GroupPublic group1 = new GroupPublic("Coca");
        group1.addNote(note1);
        group1.addNote(note2);
        group1.addEmail("fab@hotmail.fr");

        GroupPublic group2 = new GroupPublic("Pepsi");
        group1.addNote(note2);
        group1.addNote(note3);

        for (int ii = 0; ii < 1; ii++) {
            groupsList.add(group1);
            groupsList.add(group2);

        }
        //Log.i("TAG","Groups list" + groupsList.toString());




        // Add user
        User user1 = new User(1 , "fab@fghjhgh.fr", "Fabien", "paspass", true);
        User user2 = new User(2 , "dodododo@wanadoo.fr", "Cocotte", "lulu", true);
        usersList.add(user1);
        usersList.add(user2);


    }

    public static MySingleton getInstance(){
        if(mySingleton == null){
            mySingleton = new MySingleton();
            //Log.i("TAG","Singleton null");

        } else {
            //Log.i("TAG","Singleton not null");
        }

        return mySingleton;
    }


    @Override
    public String toString() {
        return "MySingleton{" +
                "groupsList=" + groupsList +
                ", usersList=" + usersList +
                '}';
    }
}
