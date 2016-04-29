package com.phd.lucas.morales.clemencio.caronte;

import android.app.ProgressDialog;
import android.os.AsyncTask;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.widget.Toast;

import com.phd.lucas.morales.clemencio.caronte.domain.User;
import com.phd.lucas.morales.clemencio.caronte.handlers.UsersHandler;
import com.phd.lucas.morales.clemencio.caronte.repository.UserRepository;

import java.util.ArrayList;
import java.util.List;

public class LoginActivity extends AppCompatActivity {

    private ProgressDialog dialog;
    List<User> userList = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        //TODO Rename this activity and references to HomeActivity.
        /*String enteredEmail = null, enteredPassword = null;
        Bundle extras = getIntent().getExtras();
        if (extras != null) {
            enteredEmail = extras.getString("email");
            enteredPassword = extras.getString("password");
        }*/
    }

    private void showToast(final String message){
        Toast.makeText(getApplicationContext(), message, Toast.LENGTH_SHORT).show();
    }
}
