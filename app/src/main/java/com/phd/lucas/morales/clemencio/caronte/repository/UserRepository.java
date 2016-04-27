package com.phd.lucas.morales.clemencio.caronte.repository;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.phd.lucas.morales.clemencio.caronte.domain.Email;
import com.phd.lucas.morales.clemencio.caronte.domain.User;
import com.phd.lucas.morales.clemencio.caronte.interfaces.CustomHandler;

/**
 * Created by Clemencio Morales Lucas on 27/04/2016.
 */
public class UserRepository {

    public static final String USERS_TABLE = "users";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String FIREBASE_ERROR = "Firebase error";
    public static final String NEW_USER_CREATED = "New user created";
    Firebase firebaseRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com");
    Firebase firebaseUsersRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com/users");
    //Create user
    //Read user
    //Read all users
    //Update user
    //Delete user

    public void addUser(final User user, final CustomHandler customHandler){
        final String encodedUserId = Email.encodeID(user.getEmail().getAddress());
        firebaseUsersRef.child(encodedUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    customHandler.handleResult(USER_ALREADY_EXISTS);
                } else {
                    Firebase firebaseUserReference = firebaseRef.child(USERS_TABLE).child(encodedUserId);
                    firebaseUserReference.setValue(user);
                    customHandler.handleResult(NEW_USER_CREATED);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                customHandler.handleResult(FIREBASE_ERROR);
            }
        });
    }
}
