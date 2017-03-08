package com.example.fabienbrun.notepostit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

public class UserView extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user_view);

        Log.i("TAG","User name is : " + MySingleton.getInstance().usersList.get(1).toString());










    }
}
