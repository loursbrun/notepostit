package com.example.fabienbrun.notepostit;


import android.app.LoaderManager;
import android.content.Loader;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.GridView;
import android.widget.SimpleCursorAdapter;

import com.example.fabienbrun.notepostit.dao.GroupDAO;
import com.example.fabienbrun.notepostit.dao.MySQLiteHelper;
import com.example.fabienbrun.notepostit.models.GroupModel;

import java.util.List;

public class GoupsPrivate extends AppCompatActivity implements LoaderManager.LoaderCallbacks<Cursor> {
    private  SimpleCursorAdapter adapter;
    private MySQLiteHelper sqLiteHelper;
    private SQLiteDatabase db;

    private final int IDLOAD = 1;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_goups_private);

        sqLiteHelper = new MySQLiteHelper(this);

        db = sqLiteHelper.getWritableDatabase();
        GroupDAO.insert(db, new GroupModel("Pulp Fiction"));
        db.close();

        db = sqLiteHelper.getReadableDatabase();
        List<GroupModel> groups = GroupDAO.getAllGroups(db);

        for(GroupModel m : groups)
            Log.i("GroupModel", m.toString());

        adapter = new SimpleCursorAdapter(this,
                android.R.layout.simple_list_item_2,
                null, //cursor chargé par le loaded
                // colonnes
                new String[]{"_id", "title"},
                // identifiants des TextView
                new int[]{android.R.id.text1, android.R.id.text2},
                // flag
                0);

        GridView gridView = (GridView) findViewById(R.id.gridView);
        gridView.setAdapter(adapter);

        getLoaderManager().initLoader(IDLOAD, null, this);

        Button reloadBtn = (Button) findViewById(R.id.btnReload);
        reloadBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                getLoaderManager().restartLoader(IDLOAD, null, GoupsPrivate.this);
            }
        });

    }

    @Override
    public Loader<Cursor> onCreateLoader(int id, Bundle args) {
        return new MyCursorLoader(getApplicationContext(), db);
    }

    @Override
    public void onLoadFinished(Loader<Cursor> loader, Cursor cursor) {
        //données disponnible
        adapter.changeCursor(cursor);
    }

    @Override
    public void onLoaderReset(Loader<Cursor> loader) {
        adapter.changeCursor(null);
    }

    public void addGroupTosql(View view) {
        db = sqLiteHelper.getWritableDatabase();
        GroupDAO.insert(db, new GroupModel("Le roi lion"));
    }

    @Override
    protected void onStop() {
        super.onStop();
        db.close();
    }
}