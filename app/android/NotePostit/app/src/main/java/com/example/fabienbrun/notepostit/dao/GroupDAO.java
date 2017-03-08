package com.example.fabienbrun.notepostit.dao;

import android.content.ContentValues;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.fabienbrun.notepostit.models.GroupModel;

import java.util.LinkedList;
import java.util.List;

/**
 * Created by fabienbrun on 07/03/2017.
 */

public class GroupDAO {private static final String TABLE_NAME = "Groups";
    private static final String COLUMN_NAME_TITLE = "title";
    private static final String COLUMN_NAME_NOTE = "note";

    // les méthodes statiques ici ont pour but d'effectuer
    // des requetes SQL pour gérer les données, les tables

    public static void create(SQLiteDatabase db) {
        db.execSQL("CREATE TABLE " + TABLE_NAME + "(" +
                "_id INTEGER PRIMARY KEY AUTOINCREMENT, " +
                COLUMN_NAME_TITLE  + " TEXT NOT NULL," + COLUMN_NAME_NOTE  + " TEXT NOT NULL )");

    }
    public static void drop(SQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS " + TABLE_NAME);
    }

    public static void insert(SQLiteDatabase db, GroupModel m )
    {

        ContentValues values = new ContentValues();
        values.put(COLUMN_NAME_TITLE, m.getTitle());
        values.put(COLUMN_NAME_NOTE, m.getNote());
        values.putNull("_id");

        db.insert(TABLE_NAME, null, values);

    }
    public static void deleteFromId(SQLiteDatabase db, long id)
    {
        db.delete(TABLE_NAME, "_id = ?", new String[] { String.valueOf(id) });
    }

    public static void deleteFromTitle(SQLiteDatabase db, String title)
    {
        db.delete(TABLE_NAME, COLUMN_NAME_TITLE  + " = ?", new String[] { title });
    }

    public static Cursor getAll(SQLiteDatabase db) {

        return db.rawQuery("SELECT  * FROM " + TABLE_NAME, null);
    }


    public static List<GroupModel> getAllGroups(SQLiteDatabase db){
        List<GroupModel> groups = new LinkedList<GroupModel>();

        String query = "SELECT  * FROM " + TABLE_NAME;

        Cursor cursor = db.rawQuery(query, null);

        GroupModel group = null;
        if (cursor.moveToFirst()) {
            //check si c'est pas null et se positionne sur la 1er ligne
            do {
                group = new GroupModel();
                group.setId(Long.parseLong(cursor.getString(0)));
                group.setTitle(cursor.getString(1));
                group.setNote(cursor.getString(1));



                groups.add(group);
            } while (cursor.moveToNext());
        }

        cursor.close();
        return groups;

    }
}

