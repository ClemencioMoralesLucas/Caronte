package com.phd.lucas.morales.clemencio.caronte.handlers;

import com.phd.lucas.morales.clemencio.caronte.domain.User;

import java.util.List;

/**
 * Created by Clemencio Morales Lucas on 27/04/2016.
 */
public interface UsersHandler {
    void handleResult(String response);
    void onUsersLoaded(List<User> users);
}
