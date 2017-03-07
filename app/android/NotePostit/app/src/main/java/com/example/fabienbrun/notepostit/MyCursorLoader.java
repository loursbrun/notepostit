package com.example.fabienbrun.notepostit;

/**
 * Created by fabienbrun on 07/03/2017.
 */

import android.content.Context;

import android.content.CursorLoader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import com.example.fabienbrun.notepostit.dao.GroupDAO;



public class MyCursorLoader extends CursorLoader {

    private SQLiteDatabase db;

    public MyCursorLoader(Context context, SQLiteDatabase db) {
        super(context);
        this.db = db;
    }


    @Override
    protected Cursor onLoadInBackground() {
        //async
        return GroupDAO.getAll(db);
    }
}
