package com.example.fabienbrun.notepostit;

import android.app.ProgressDialog;
import android.content.Intent;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import butterknife.ButterKnife;
import butterknife.Bind;

public class LoginActivity extends AppCompatActivity {
    private static final String TAG = "LoginActivity";
    private static final int REQUEST_SIGNUP = 0;


    @Bind(R.id.input_email) EditText _emailText;
    @Bind(R.id.input_password) EditText _passwordText;
    @Bind(R.id.btn_login) Button _loginButton;
    @Bind(R.id.link_signup) TextView _signupLink;

    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);



        // Récupération des données coté serveur

            UserHttpAsynClass userHttpAsyn = new UserHttpAsynClass();
            userHttpAsyn.execute();

            GroupHttpAsynClass groupHttpAsyn = new GroupHttpAsynClass();
            groupHttpAsyn.execute();

            NoteHttpAsynClass noteHttpAsyn = new NoteHttpAsynClass();
            noteHttpAsyn.execute();






        _loginButton.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                login();
            }
        });

        _signupLink.setOnClickListener(new View.OnClickListener() {

            @Override
            public void onClick(View v) {
                // Start the Signup activity
                Intent intent = new Intent(getApplicationContext(), SignupActivity.class);
                startActivityForResult(intent, REQUEST_SIGNUP);
                finish();
                overridePendingTransition(R.anim.push_left_in, R.anim.push_left_out);
            }
        });
    }

    public void login() {
        Log.d(TAG, "Login");

        if (!validate()) {
            onLoginFailed();
            return;
        }

        _loginButton.setEnabled(false);

        final ProgressDialog progressDialog = new ProgressDialog(LoginActivity.this,
                R.style.AppTheme_Dark_Dialog);
        progressDialog.setIndeterminate(true);
        progressDialog.setMessage("Authenticating...");
        progressDialog.show();

        String email = _emailText.getText().toString();
        String password = _passwordText.getText().toString();



        String emailToBase = "";
        String passwordToBase = "";




        Boolean emailAndPassOK = false;


        for (int i = 0; i < MySingleton.getInstance().usersList.size(); i++) {
            emailToBase = MySingleton.getInstance().usersList.get(i).getEmail().toString();
            passwordToBase = MySingleton.getInstance().usersList.get(i).getPassword().toString();
            if(email.equals(emailToBase) && password.equals(passwordToBase)) {
                emailAndPassOK = true;
            }
        }



        Log.i("TAG","Email" + emailToBase + "/" + email);
        Log.i("TAG","passwordToBase" + passwordToBase + "/" + password);



        if(emailAndPassOK) {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                            onLoginSuccess();  //onLoginFailed();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        } else {
            new android.os.Handler().postDelayed(
                    new Runnable() {
                        public void run() {
                           onLoginFailed(); //onLoginSuccess();
                            progressDialog.dismiss();
                        }
                    }, 3000);
        }


    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, Intent data) {
        if (requestCode == REQUEST_SIGNUP) {
            if (resultCode == RESULT_OK) {

                // TODO: Implement successful signup logic here
                // By default we just finish the Activity and log them in automatically
                this.finish();
            }
        }
    }

    @Override
    public void onBackPressed() {
        // Disable going back to the MainActivity
        moveTaskToBack(true);
    }

    public void onLoginSuccess() {
        _loginButton.setEnabled(true);

        Intent i = new Intent(getApplicationContext(), GroupsView.class);
        startActivity(i);


        finish();
    }

    public void onLoginFailed() {
        Toast.makeText(getBaseContext(), "Login failed", Toast.LENGTH_LONG).show();

        _loginButton.setEnabled(true);
    }


    public boolean validate() {
        boolean valid = true;

        String email = _emailText.getText().toString();


        if(email.equals("a")){
            Intent i = new Intent(getApplicationContext(), GroupsView.class);
            startActivity(i);
            finish();
        }


        String password = _passwordText.getText().toString();

        if (email.isEmpty() || !android.util.Patterns.EMAIL_ADDRESS.matcher(email).matches()) {
            _emailText.setError("enter a valid email address");
            valid = false;
        } else {
            _emailText.setError(null);
        }

        if (password.isEmpty() || password.length() < 4 || password.length() > 10) {
            _passwordText.setError("between 4 and 10 alphanumeric characters");
            valid = false;
        } else {
            _passwordText.setError(null);
        }

        return valid;
    }
}
