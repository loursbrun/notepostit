package com.example.fabienbrun.notepostit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import com.example.fabienbrun.notepostit.rest.HttpRestAsync;

public class MainActivity extends AppCompatActivity implements HttpRestAsync.OnHttpRestAsyncComplete{

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        // Redirection GroupsView
        //Intent i = new Intent(getApplicationContext(), NotesView.class);
        //Intent i = new Intent(getApplicationContext(), GroupsView.class);
        //Intent i = new Intent(getApplicationContext(), UserView.class);


        //Intent i = new Intent(getApplicationContext(), LoginActivity.class);
        //startActivity(i);


    }


    @Override
    protected void onResume() {
        super.onResume();
        new HttpRestAsync(this, getString(R.string.host) + "/groupPublics").execute();
        new HttpRestAsync(this, getString(R.string.host) + "/groupPublics/3","DELETE",null).execute();
    }



    @Override
    public void onComplete(String result) {
        Log.i("TAG","RESULT:" + result );
    }
}
