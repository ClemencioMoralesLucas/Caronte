package com.phd.lucas.morales.clemencio.caronte.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Clemencio Morales Lucas on 01/04/2016.
 */
public class PasswordTest {

    private Password password;

    public static final String VALID_PASSWORD_1 = "Password8@";
    public static final String VALID_PASSWORD_2 = "AtleticoDeMadrid111#";
    public static final String VALID_PASSWORD_3 = "1Solid#1#Snake1";
    public static final String VALID_PASSWORD_4 = "L1qu1DSn4k3.";
    public static final String VALID_PASSWORD_5 = "Revolver...Ocelot#1";
    public static final String VALID_PASSWORD_6 = "#Vulcan.0.Raven#";

    public static final String INVALID_PASSWORD_1 = "Passwd";
    public static final String INVALID_PASSWORD_2 = "abc";
    public static final String INVALID_PASSWORD_3 = "1234";
    public static final String INVALID_PASSWORD_4 = "Pas sword1";
    public static final String INVALID_PASSWORD_5 = "Pass wo    rd2";
    public static final String INVALID_PASSWORD_6 = " password123@ ";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        this.password = new Password();
    }

    @Test
    public void setValidPassword1DoesNotThrowException(){
        this.password.setPassword(VALID_PASSWORD_1);
    }

    @Test
    public void setValidPassword2DoesNotThrowException(){
        this.password.setPassword(VALID_PASSWORD_2);
    }

    @Test
    public void setValidPassword3DoesNotThrowException(){
        this.password.setPassword(VALID_PASSWORD_3);
    }

    @Test
    public void setValidPassword4DoesNotThrowException(){
        this.password.setPassword(VALID_PASSWORD_4);
    }

    @Test
    public void setValidPassword5DoesNotThrowException(){
        this.password.setPassword(VALID_PASSWORD_5);
    }

    @Test
    public void setValidPassword6DoesNotThrowException(){
        this.password.setPassword(VALID_PASSWORD_6);
    }

    @Test
    public void setInvalidPassword1ThrowsException(){
        getReadyForException(Password.PASSWORD_NOT_VALID);
        this.password.setPassword(INVALID_PASSWORD_1);
    }

    @Test
    public void setInvalidPassword2ThrowsException(){
        getReadyForException(Password.PASSWORD_NOT_VALID);
        this.password.setPassword(INVALID_PASSWORD_2);
    }

    @Test
    public void setInvalidPassword3ThrowsException(){
        getReadyForException(Password.PASSWORD_NOT_VALID);
        this.password.setPassword(INVALID_PASSWORD_3);
    }

    @Test
    public void setInvalidPassword4ThrowsException(){
        getReadyForException(Password.PASSWORD_NOT_VALID);
        this.password.setPassword(INVALID_PASSWORD_4);
    }

    @Test
    public void setInvalidPassword5ThrowsException(){
        getReadyForException(Password.PASSWORD_NOT_VALID);
        this.password.setPassword(INVALID_PASSWORD_5);
    }

    @Test
    public void setInvalidPassword6ThrowsException(){
        getReadyForException(Password.PASSWORD_NOT_VALID);
        this.password.setPassword(INVALID_PASSWORD_6);
    }

    public void getReadyForException(final String message) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(message);
    }
}