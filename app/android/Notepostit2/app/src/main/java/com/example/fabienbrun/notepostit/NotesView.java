package com.example.fabienbrun.notepostit;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import java.util.ArrayList;

public class NotesView extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        mTaskListView = (ListView) findViewById(R.id.list_todo);

        Log.i("TAG","Notes !!!!! " + MySingleton.getInstance().notesList.size());




        updateUI();


    }


    private void updateUI() {


        ArrayList<String> taskList = new ArrayList<>();


            for (int i = 0; i < MySingleton.getInstance().groupsList.get(0).getNoteListe().size(); i++) {

                taskList.add(MySingleton.getInstance().notesList.get(i).getName().toString());

            }



        if (mAdapter == null) {
            mAdapter = new ArrayAdapter<>(this,
                    R.layout.note_item,
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


    // Update Task
    protected void onListUpdateClick(View view) {

        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());


        final EditText taskEditText = new EditText(this);
        taskEditText.setText(task);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Add a new task")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setIcon(R.drawable.img2)
                .setPositiveButton("Update", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {




                        // Add new
                        ContentValues values = new ContentValues();


                        updateUI();

                    }
                })
                .setNegativeButton("Cancel", null)
                .create();

        dialog.show();
    }




}
