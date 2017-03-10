package com.example.fabienbrun.notepostit;

import android.content.ContentValues;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;

import com.example.fabienbrun.notepostit.note.Note;

import java.util.ArrayList;

public class NotesView extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;
    private int projetID ;
    private View currentView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notes_view);

        mTaskListView = (ListView) findViewById(R.id.list_todo);



        // Intent pour récupérer le numéro du projet cliqué
        // Intent pour récupérer le numéro du projet cliqué
        Intent i = getIntent();
        i.getStringExtra("msg");
        projetID = i.getExtras().getInt("idProjet");


        updateUI();

    }


    private void updateUI() {



        // Récupération des données coté serveur

        UserHttpAsynClass userHttpAsyn = new UserHttpAsynClass();
        userHttpAsyn.execute();

        GroupHttpAsynClass groupHttpAsyn = new GroupHttpAsynClass();
        groupHttpAsyn.execute();

        NoteHttpAsynClass noteHttpAsyn = new NoteHttpAsynClass();
        noteHttpAsyn.execute();


        ArrayList<String> taskList = new ArrayList<>();

            for (int i = 0; i < MySingleton.getInstance().notesList.size(); i++) {


                if(MySingleton.getInstance().notesList.get(i).getGroupID() == (projetID + 1)) {
                    Log.i("TAG","notes id !!!!! " +  MySingleton.getInstance().notesList.get(i));
                    taskList.add(MySingleton.getInstance().notesList.get(i).getName().toString());
                }

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

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new Note")
                        .setMessage("")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String groupName = String.valueOf(taskEditText.getText());






                                MySingleton.getInstance().addNoteList(new Note(50,"fghjkl","vghjkl","ghjkl",1));

                                updateUI();


                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();
                return true;

            default:
                return super.onOptionsItemSelected(item);
        }
    }





    protected void deleteNote(View view) {
        currentView = view;
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String noteTitle = String.valueOf(taskTextView.getText());

        for (int i = 0; i < MySingleton.getInstance().notesList.size(); i++) {
            if( MySingleton.getInstance().notesList.get(i).getName().toString().equals(noteTitle)){

                MySingleton.getInstance().notesList.remove(i);
                updateUI();


            }
        }
    }




    // Update Task
    protected void onListUpdateClick(View view) {

        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String task = String.valueOf(taskTextView.getText());


        final EditText taskEditText = new EditText(this);
        taskEditText.setText(task);

        AlertDialog dialog = new AlertDialog.Builder(this)
                .setTitle("Update the Note !")
                .setMessage("What do you want to do next?")
                .setView(taskEditText)
                .setIcon(R.drawable.etat1)
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
