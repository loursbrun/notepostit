package com.example.fabienbrun.notepostit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Redirection GroupsView
        //Intent i = new Intent(getApplicationContext(), GroupsView.class);
        //Intent i = new Intent(getApplicationContext(), UserView.class);


        Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        startActivity(i);




    }
}
