package com.phd.lucas.morales.clemencio.caronte.domain;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */

public interface EmailAndPasswordConstants {

    /**
    * Email address pattern RFC 5322 Official Standard
    */
    String EMAIL_REGEX = "(?:[a-z0-9!#$%&'*+/=?^_`{|}~-]+(?:\\.[a-z0-9!#$%&'*+/=?^_`{|}~-]+)*|\"(?:" +
            "[\\x01-\\x08\\x0b\\x0c\\x0e-\\x1f\\x21\\x23-\\x5b\\x5d-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])*\")" +
            "@(?:(?:[a-z0-9](?:[a-z0-9-]*[a-z0-9])?\\.)+[a-z0-9](?:[a-z0-9-]*[a-z0-9])?|\\[(?:(?:25[0-5]|2[0-4][0-9]|[01" +
            "]?[0-9][0-9]?)\\.){3}(?:25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?|[a-z0-9-]*[a-z0-9]:(?:[\\x01-\\x08\\x0b\\x0" +
            "c\\x0e-\\x1f\\x21-\\x5a\\x53-\\x7f]|\\\\[\\x01-\\x09\\x0b\\x0c\\x0e-\\x7f])+)\\])";

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
}
