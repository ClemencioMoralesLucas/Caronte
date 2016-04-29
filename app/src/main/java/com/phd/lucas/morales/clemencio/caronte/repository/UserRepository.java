package com.phd.lucas.morales.clemencio.caronte.repository;

import android.content.Context;

import com.firebase.client.DataSnapshot;
import com.firebase.client.Firebase;
import com.firebase.client.FirebaseError;
import com.firebase.client.ValueEventListener;
import com.phd.lucas.morales.clemencio.caronte.domain.Email;
import com.phd.lucas.morales.clemencio.caronte.domain.Password;
import com.phd.lucas.morales.clemencio.caronte.domain.User;
import com.phd.lucas.morales.clemencio.caronte.handlers.UsersHandler;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by Clemencio Morales Lucas on 27/04/2016.
 */
public class UserRepository {

    public static final String USERS_TABLE = "users";
    public static final String USER_ALREADY_EXISTS = "User already exists";
    public static final String FIREBASE_ERROR = "Firebase error";
    public static final String NEW_USER_CREATED = "New user created";
    Firebase firebaseRef;
    Firebase firebaseUsersRef;

    //TODO Remaining methods
    //Fix retrieveAllUsers
    //retrieveUser
    //Update user
    //Delete user

    public UserRepository(Context context){
        Firebase.setAndroidContext(context);
        firebaseRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com");
        firebaseUsersRef = new Firebase("https://clemencio-morales-lucas-caronte.firebaseio.com/users");
    }

    public void addUser(final User user, final UsersHandler usersHandler){
        final String encodedUserId = Email.encodeID(user.getEmail().getAddress());
        firebaseUsersRef.child(encodedUserId).addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot snapshot) {
                if (snapshot.exists()) {
                    usersHandler.handleResult(USER_ALREADY_EXISTS);
                } else {
                    Firebase firebaseUserReference = firebaseRef.child(USERS_TABLE).child(encodedUserId);
                    firebaseUserReference.setValue(user);
                    usersHandler.handleResult(NEW_USER_CREATED);
                }
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
                usersHandler.handleResult(FIREBASE_ERROR);
            }
        });
    }

    //Mocked method, // FIXME: 29/04/2016
    public User retrieveUserByEmail(String email){
        User user = new User();
        user.setEmail(new Email("mail@clemenciomorales.com"));
        user.setPassword(new Password("pepepepe"));
        return user;
    }

    public void retrieveAllUsers(final UsersHandler usersHandler) {
        final List<User> users = new ArrayList<User>();
        firebaseUsersRef.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                for (DataSnapshot snapshot : dataSnapshot.getChildren()) {
                    User user = snapshot.getValue(User.class);
                    users.add(user);
                }
                usersHandler.onUsersLoaded(users);
            }

            @Override
            public void onCancelled(FirebaseError firebaseError) {
            }
        });
    }

    /*
    TODO Extracted from Firebase docs, implement it
    public void updateUser(){
        Firebase alanRef = usersRef.child("alanisawesome");
        Map<String, Object> nickname = new HashMap<String, Object>();
        nickname.put("nickname", "Alan The Machine");
        alanRef.updateChildren(nickname);
    }*/
}
