package com.phd.lucas.morales.clemencio.caronte.domain;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */

import java.util.regex.Pattern;

/**
 * General Email class (According to RFC 5322 Official Standard)
 */
public class Email {

    /**
     * Email address pattern RFC 5322 Official Standard
     */
    String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01" +
            "]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0" +
            "c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static final String EMAIL_NOT_VALID = "The email address provided is not valid.";
    private String address;

    Pattern emailPattern = Pattern.compile(EMAIL_REGEX);

    public Email(final String address){
        this.setAddress(address);
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
}
