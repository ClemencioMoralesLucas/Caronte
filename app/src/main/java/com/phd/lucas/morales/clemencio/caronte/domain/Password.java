package com.phd.lucas.morales.clemencio.caronte.domain;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */

import java.util.regex.Pattern;

import lombok.ToString;

/**
 * Custom Password class
 */

@ToString(exclude = "PASSWORD_REGEX, passwordPattern")
public class Password {

    /**
     * The current password policy is the following:
     -At least 8 chars

     -Does not contain space.

     The individual rules can be added to the regex in order to create an stronger password criteria:

     ^                 # start-of-string
     (?=.*[0-9])       # a digit must occur at least once
     (?=.*[a-z])       # a lower case letter must occur at least once
     (?=.*[A-Z])       # an upper case letter must occur at least once
     (?=.*[@#$%^&+=])  # a special character must occur at least once
     (?=\S+$)          # no whitespace allowed in the entire string
     .{8,}             # anything, at least eight places though
     $                 # end-of-string

     *
     */
    String PASSWORD_REGEX = "^.*(?=.{8,})(?=\\S+$).*$";

    public static final String PASSWORD_NOT_VALID = "The password provided is not valid. The password should has at least 8 chars and does not contain any space.";
    private String password;

    Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    public Password(final String password){
        this.setPassword(password);
    }

    public Password(){}

    public void setPassword(final String password){
        if(passwordPattern.matcher(password).matches()){
            this.password = password;
        } else {
            throw new IllegalArgumentException(PASSWORD_NOT_VALID);
        }
    }

    public String getPassword(){
        return this.password;
    }
}
