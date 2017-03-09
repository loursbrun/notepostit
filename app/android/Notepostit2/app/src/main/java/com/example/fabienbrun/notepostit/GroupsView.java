package com.example.fabienbrun.notepostit;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.Menu;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class GroupsView extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_view);

        mTaskListView = (ListView) findViewById(R.id.list_todo);

        //Log.i("TAG","MyList Size" + MySingleton.getInstance().groupsList.size());
        updateUI();

    }


    private void updateUI() {


        ArrayList<String> taskList = new ArrayList<>();

        for (int i = 0; i < MySingleton.getInstance().groupsList.size(); i++) {
            taskList.add(MySingleton.getInstance().groupsList.get(i).getName().toString());
        }

        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.item_todo,
                    R.id.task_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }

    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



}
