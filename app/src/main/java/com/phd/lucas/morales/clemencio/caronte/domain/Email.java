package com.phd.lucas.morales.clemencio.caronte.domain;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */
import android.content.Context;

import java.util.regex.Pattern;

/**
 * General Email class (According to RFC 5322 Official Standard)
 */
public class Email implements EmailConstants {

    public static final String EMAIL_NOT_VALID = "The email address provided is not valid.";
    public static final String PASSWORD_NOT_VALID = "The password provided is not valid. The password should has at least 8 chars and does not contain any space or tab.";
    private String address;
    private String password;

    Pattern emailPattern = Pattern.compile(EMAIL_REGEX);
    Pattern passwordPattern = Pattern.compile(PASSWORD_REGEX);

    public Email(final String address, final String password){
        this.setAddress(address);
        this.setPassword(password);
    }

    public Email(){}

    public void setAddress(final String address){
        if(emailPattern.matcher(address).matches()){
            this.address = address;
        } else {
            throw new IllegalArgumentException(EMAIL_NOT_VALID);
        }
    }

    public String getAddress(){
        return this.address;
    }

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
