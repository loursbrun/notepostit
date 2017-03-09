package com.example.fabienbrun.notepostit;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.ArrayList;

public class GroupsView extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;
    private View currentView;

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
                    R.layout.group_item,
                    R.id.task_title,
                    taskList);
            mTaskListView.setAdapter(mAdapter);
        } else {
            mAdapter.clear();
            mAdapter.addAll(taskList);
            mAdapter.notifyDataSetChanged();
        }




        mTaskListView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Log.i("TAG", "Position=" + position);

                Toast.makeText(getApplicationContext(), "Test", Toast.LENGTH_SHORT).show();
            }
        });


    }


    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        getMenuInflater().inflate(R.menu.menu_main, menu);
        return super.onCreateOptionsMenu(menu);
    }



    // Update Task
    protected void onListUpdateClick(View view) {

        currentView = view;
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String groupTitle = String.valueOf(taskTextView.getText());

        for (int i = 0; i < MySingleton.getInstance().groupsList.size(); i++) {
            if( MySingleton.getInstance().groupsList.get(i).getName().toString().equals(groupTitle)){
                Log.i("TAG", "iiiiiiiii" + i );

                Intent inte = new Intent(view.getContext(), NotesView.class);
                inte.putExtra("idProjet", i); // key value
                startActivity(inte);

            }

        }






    }




}
