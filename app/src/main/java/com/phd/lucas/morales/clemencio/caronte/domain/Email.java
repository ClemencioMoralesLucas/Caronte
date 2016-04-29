package com.phd.lucas.morales.clemencio.caronte.domain;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */

import java.util.regex.Pattern;

import lombok.ToString;

/**
 * General Email class (According to RFC 5322 Official Standard)
 */

@ToString(exclude="EMAIL_REGEX, emailPattern")
public class Email {

    /**
     * Email address pattern RFC 5322 Official Standard
     */
    String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01" +
            "]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0" +
            "c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

    public static final String UNALLOWED_FIREBASE_CHARACTER_DOT = ".";
    public static final String UNALLOWED_FIREBASE_CHARACTER_HASHTAG = "#";
    public static final String UNALLOWED_FIREBASE_CHARACTER_DOLLAR = "$";
    public static final String UNALLOWED_FIREBASE_CHARACTER_OPEN_BRACKET = "[";
    public static final String UNALLOWED_FIREBASE_CHARACTER_CLOSE_BRACKET = "]";

    public static final String ESCAPED_CHARACTER_CODE_DOT = "%2E";
    public static final String ESCAPED_CHARACTER_CODE_HASHTAG = "%23";
    public static final String ESCAPED_CHARACTER_CODE_DOLLAR = "%24";
    public static final String ESCAPED_CHARACTER_CODE_OPEN_BRACKET = "%5B";
    public static final String ESCAPED_CHARACTER_CODE_CLOSE_BRACKET = "%5D";

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

    public static String encodeID(String userId) {
        /*
        * Firebase does not allow IDs to contain the characters
        * ".", "#", "$", "[" and "]", thus they are mapped into
        * the consequent URL standard encoding.
        * */
        userId = userId.replace(UNALLOWED_FIREBASE_CHARACTER_DOT, ESCAPED_CHARACTER_CODE_DOT);
        userId = userId.replace(UNALLOWED_FIREBASE_CHARACTER_HASHTAG, ESCAPED_CHARACTER_CODE_HASHTAG);
        userId = userId.replace(UNALLOWED_FIREBASE_CHARACTER_DOLLAR, ESCAPED_CHARACTER_CODE_DOLLAR);
        userId = userId.replace(UNALLOWED_FIREBASE_CHARACTER_OPEN_BRACKET, ESCAPED_CHARACTER_CODE_OPEN_BRACKET);
        userId = userId.replace(UNALLOWED_FIREBASE_CHARACTER_CLOSE_BRACKET, ESCAPED_CHARACTER_CODE_CLOSE_BRACKET);
        return userId;
    }

    public static String decodeID(String userId) {
        /*
        * In order to know what is the actual email address saved
        * as ID, we can independently access User.email attribute
         * or decode the ID with this method.
        * */
        userId = userId.replace(ESCAPED_CHARACTER_CODE_DOT, UNALLOWED_FIREBASE_CHARACTER_DOT);
        userId = userId.replace(ESCAPED_CHARACTER_CODE_HASHTAG, UNALLOWED_FIREBASE_CHARACTER_HASHTAG);
        userId = userId.replace(ESCAPED_CHARACTER_CODE_DOLLAR, UNALLOWED_FIREBASE_CHARACTER_DOLLAR);
        userId = userId.replace(ESCAPED_CHARACTER_CODE_OPEN_BRACKET, UNALLOWED_FIREBASE_CHARACTER_OPEN_BRACKET);
        userId = userId.replace(ESCAPED_CHARACTER_CODE_CLOSE_BRACKET, UNALLOWED_FIREBASE_CHARACTER_CLOSE_BRACKET);
        return userId;
    }
}
