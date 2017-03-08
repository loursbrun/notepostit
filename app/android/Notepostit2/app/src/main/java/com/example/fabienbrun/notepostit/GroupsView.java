package com.example.fabienbrun.notepostit;

import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;

public class GroupsView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_view);


        Log.i("TAG","MyList Size" + MySingleton.getInstance().groupsList.size());


    }
}
