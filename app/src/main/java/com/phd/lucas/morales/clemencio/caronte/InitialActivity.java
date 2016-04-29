package com.phd.lucas.morales.clemencio.caronte;

import android.content.Intent;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import com.firebase.client.Firebase;
import com.phd.lucas.morales.clemencio.caronte.domain.Email;
import com.phd.lucas.morales.clemencio.caronte.domain.Password;
import com.phd.lucas.morales.clemencio.caronte.domain.User;
import com.phd.lucas.morales.clemencio.caronte.handlers.UsersHandler;
import com.phd.lucas.morales.clemencio.caronte.repository.UserRepository;

import java.util.List;

public class InitialActivity extends AppCompatActivity {

    UserRepository userRepository;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        userRepository = new UserRepository(this.getApplicationContext());
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
                Intent myIntent = new Intent(InitialActivity.this, LoginActivity.class);

                String email = getEmail();
                String password = getPassword();

                boolean success = true;
                User user = new User();
                try {
                    user.setEmail(new Email(email));
                    user.setPassword(new Password(password));
                } catch (Exception e) {
                    success = false;
                    showToast(e.getMessage());
                }

                if(isValidCredential(email, password)) {
                    showToast("Login OK");
                } else {
                    showToast("Invalid credentials");
                    success = false;
                }

                if (success) {
                    //TODO Investigate login approaches in Android
                    myIntent.putExtra("user_authenticated", true);
                    InitialActivity.this.startActivity(myIntent);
                }
            }
        });
    }


    private boolean isValidCredential(final String enteredEmail, final String enteredPassword){
        boolean success = false;
        User user = userRepository.retrieveUserByEmail(enteredEmail);

        if(user.getEmail().getAddress().equals(enteredEmail)){
            if(user.getPassword().getPassword().equals(enteredPassword)){
                success = true;
            }
        }
        return success;
    }

    private void retrieveAllUsers() {
        userRepository.retrieveAllUsers(new UsersHandler() {
            @Override
            public void handleResult(String response) {
            }

            @Override
            public void onUsersLoaded(List<User> users) {
                for (User item : users) {
                    //userList.add(item);
                    //showToast(item.toString());
                }
            }
        });
    }

    @NonNull
    private String getEmail() {
        EditText mEmail = (EditText) findViewById(R.id.editTextEmail);
        return mEmail.getText().toString();
    }

    @NonNull
    private String getPassword(){
        EditText mPassword = (EditText) findViewById(R.id.editTextPassword);
        return mPassword.getText().toString();
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

    private void showToast(final String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
