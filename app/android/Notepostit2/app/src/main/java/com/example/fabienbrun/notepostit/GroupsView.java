package com.example.fabienbrun.notepostit;

import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AlertDialog;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.fabienbrun.notepostit.group.GroupPublic;

import java.util.ArrayList;

public class GroupsView extends AppCompatActivity {

    private ArrayAdapter<String> mAdapter;
    private ListView mTaskListView;
    private View currentView;
    private int currentGroup;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_groups_view);

        mTaskListView = (ListView) findViewById(R.id.list_todo);

        //Log.i("TAG","MyList Size" + MySingleton.getInstance().groupsList.size());
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



    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        switch (item.getItemId()) {
            case R.id.action_add_task:
                final EditText taskEditText = new EditText(this);
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Add a new Group")
                        .setMessage("You can share it width yours friends !")
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String groupName = String.valueOf(taskEditText.getText());

                                MySingleton.getInstance().groupsList.add(new GroupPublic(groupName));

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



    // Update Task
    protected void onListUpdateClick(View view) {
        currentView = view;
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String groupTitle = String.valueOf(taskTextView.getText());

        for (int i = 0; i < MySingleton.getInstance().groupsList.size(); i++) {
            if( MySingleton.getInstance().groupsList.get(i).getName().toString().equals(groupTitle)){
               // Log.i("TAG", "iiiiiiiii" + i );

                Intent inte = new Intent(view.getContext(), NotesView.class);
                inte.putExtra("idProjet", i); // key value
                startActivity(inte);
            }
        }
    }



    // Delete Group
    protected void deleteGroup(View view) {
        currentView = view;
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String groupTitle = String.valueOf(taskTextView.getText());

        for (int i = 0; i < MySingleton.getInstance().groupsList.size(); i++) {
            if( MySingleton.getInstance().groupsList.get(i).getName().toString().equals(groupTitle)){

                MySingleton.getInstance().groupsList.remove(i);
                updateUI();



                // Requête HTTP delete Group







            }
        }
    }


    // Share Group width email
    protected void shareGroup(View view) {
        currentView = view;
        View parent = (View) view.getParent();
        TextView taskTextView = (TextView) parent.findViewById(R.id.task_title);
        String groupTitle = String.valueOf(taskTextView.getText());

        for (int i = 0; i < MySingleton.getInstance().groupsList.size(); i++) {
            if( MySingleton.getInstance().groupsList.get(i).getName().toString().equals(groupTitle)){

                currentGroup = i;
                final EditText taskEditText = new EditText(this);





                String listeEmails = "";
                // Loop through elements.
                for (int ii = 0; ii < MySingleton.getInstance().groupsList.get(currentGroup).getEmails().size(); ii++) {
                    if(ii != 0){listeEmails += " , ";}
                    listeEmails += MySingleton.getInstance().groupsList.get(currentGroup).getEmails().get(i).toString();

                }
                AlertDialog dialog = new AlertDialog.Builder(this)
                        .setTitle("Enter an Email to share it !")
                        .setMessage(listeEmails)
                        .setView(taskEditText)
                        .setPositiveButton("Add", new DialogInterface.OnClickListener() {
                            @Override
                            public void onClick(DialogInterface dialog, int which) {
                                String newEmail = String.valueOf(taskEditText.getText());

                                MySingleton.getInstance().groupsList.get(currentGroup).addEmail(newEmail);

                                updateUI();
                            }
                        })
                        .setNegativeButton("Cancel", null)
                        .create();
                dialog.show();


            }
        }
    }












}
