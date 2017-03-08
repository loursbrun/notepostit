package com.example.fabienbrun.notepostit.dao;

import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;

/**
 * Created by fabienbrun on 07/03/2017.
 */

public class MySQLiteHelper extends SQLiteOpenHelper {

        // nom du fichier contenant la base de données
        private static final String DB_NAME = "groupDB9.db";
        private static final int DB_VERSION = 8;

        public MySQLiteHelper(Context context)
        {
            super(context, DB_NAME, null, DB_VERSION);
        }

        @Override
        public void onCreate(SQLiteDatabase db) {

            GroupDAO.create(db);
        }

        @Override
        public void onUpgrade(SQLiteDatabase db, int oldVersion, int newVersion) {
            GroupDAO.drop(db);
            onCreate(db);
            //en prod on ferait plutot un alterTable si nécéssaire
            if (oldVersion == 2) {
                //ALTER TABLE
                // ..
            }
        }
    }

