package com.phd.lucas.morales.clemencio.caronte.domain;

import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;

/**
 * Created by Clemencio Morales Lucas on 01/04/2016.
 */
public class EmailTest {

    private Email email;

    public static final String VALID_EMAIL_1 = "clemencio@upm.es";
    public static final String VALID_EMAIL_2 = "frank.castle@upm.es";
    public static final String VALID_EMAIL_3 = "clemencio.morales.lucas@upm.es";
    public static final String VALID_EMAIL_4 = "transport@gov.co.uk";
    public static final String VALID_EMAIL_5 = "somebody.cool@gmail.com";
    public static final String VALID_EMAIL_6 = "anyone@192.168.0.1";
    public static final String VALID_EMAIL_7 = "jaquemate@gmail.com";

    public static final String INVALID_EMAIL_1 = "Frank Castle";
    public static final String INVALID_EMAIL_2 = "mail@x";
    public static final String INVALID_EMAIL_3 = "fake.email@332#";
    public static final String INVALID_EMAIL_4 = "@upm.es";
    public static final String INVALID_EMAIL_5 = "clemen@upm .es";
    public static final String INVALID_EMAIL_6 = "clemen @upm.es";

    @Rule
    public ExpectedException exception = ExpectedException.none();

    @Before
    public void setUp(){
        this.email = new Email();
    }

    @Test
    public void setValidEmail1DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_1);
    }

    @Test
    public void setValidEmail2DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_2);
    }

    @Test
    public void setValidEmail3DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_3);
    }

    @Test
    public void setValidEmail4DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_4);
    }

    @Test
    public void setValidEmail5DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_5);
    }

    @Test
    public void setValidEmail6DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_6);
    }

    @Test
    public void setValidEmail7DoesNotThrowException(){
        this.email.setAddress(VALID_EMAIL_7);
    }

    @Test
    public void setInvalidEmail1ThrowsException(){
        getReadyForException(Email.EMAIL_NOT_VALID);
        this.email.setAddress(INVALID_EMAIL_1);
    }

    @Test
    public void setInvalidEmail2ThrowsException(){
        getReadyForException(Email.EMAIL_NOT_VALID);
        this.email.setAddress(INVALID_EMAIL_2);
    }

    @Test
    public void setInvalidEmail3ThrowsException(){
        getReadyForException(Email.EMAIL_NOT_VALID);
        this.email.setAddress(INVALID_EMAIL_3);
    }

    @Test
    public void setInvalidEmail4ThrowsException(){
        getReadyForException(Email.EMAIL_NOT_VALID);
        this.email.setAddress(INVALID_EMAIL_4);
    }

    @Test
    public void setInvalidEmail5ThrowsException(){
        getReadyForException(Email.EMAIL_NOT_VALID);
        this.email.setAddress(INVALID_EMAIL_5);
    }

    @Test
    public void setInvalidEmail6ThrowsException(){
        getReadyForException(Email.EMAIL_NOT_VALID);
        this.email.setAddress(INVALID_EMAIL_6);
    }

    public void getReadyForException(final String message) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(message);
    }
}