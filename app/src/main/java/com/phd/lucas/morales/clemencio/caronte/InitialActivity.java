package com.phd.lucas.morales.clemencio.caronte;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;

public class InitialActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setupFirebase();
        setContentView(R.layout.activity_initial);
        setupButtons();
    }

    private void setupFirebase() {
        //TODO See below to see how to create users
        Firebase.setAndroidContext(this);
        //Firebase myFirebaseRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com/");
        //myFirebaseRef.child("message").setValue("Do you have data? You'll love Firebase.");

        //Firebase myFirebaseRef2 = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com/users/");
        //myFirebaseRef2.child("user1").setValue("Foo");
    }

    private void setupButtons() {
        loginButton();
        registerButton();
        forgotPasswordButton();
    }

    private void loginButton() {
        final Button loginButton = (Button) findViewById(R.id.loginButton);
        loginButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Login pressed", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(InitialActivity.this, LoginActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                InitialActivity.this.startActivity(myIntent);
            }
        });
    }

    private void registerButton() {
        final Button registerButton = (Button) findViewById(R.id.registerButton);
        registerButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Register pressed", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(InitialActivity.this, RegisterActivity.class);
                EditText mEmail = (EditText)findViewById(R.id.editTextEmail);
                EditText mPassword = (EditText)findViewById(R.id.editTextPassword);

                String email = mEmail.getText().toString();
                String password = mPassword.getText().toString();

                myIntent.putExtra("email", email); //Optional parameters
                myIntent.putExtra("password", password); //Optional parameters

                InitialActivity.this.startActivity(myIntent);
            }
        });
    }

    private void forgotPasswordButton() {
        final Button forgotPasswordButton = (Button) findViewById(R.id.forgotPasswordButton);
        forgotPasswordButton.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                //Toast.makeText(getApplicationContext(), "Forgot password pressed", Toast.LENGTH_SHORT).show();

                Intent myIntent = new Intent(InitialActivity.this, ForgotPasswordActivity.class);
                //myIntent.putExtra("key", value); //Optional parameters
                InitialActivity.this.startActivity(myIntent);
            }
        });
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.menu_login, menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle action bar item clicks here. The action bar will
        // automatically handle clicks on the Home/Up button, so long
        // as you specify a parent activity in AndroidManifest.xml.
        int id = item.getItemId();

        //noinspection SimplifiableIfStatement
        if (id == R.id.action_settings) {
            return true;
        }

        return super.onOptionsItemSelected(item);
    }

    /*protected void onCreate(Bundle icicle) {
        super.onCreate(icicle);

        setContentView(R.layout.content_login);

        final Button button = (Button) findViewById(R.id.loginButton);
        button.setOnClickListener(new View.OnClickListener() {
            public void onClick(View v) {
                Toast.makeText(getApplicationContext(), "Login pressed", Toast.LENGTH_SHORT).show();
            }
        });
    }*/
}
