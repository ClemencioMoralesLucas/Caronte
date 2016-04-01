package com.phd.lucas.morales.clemencio.caronte.domain;

/*
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.rules.ExpectedException;
*/
/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */
public class EmailTest {

    private Email email;

    public static final String VALID_EMAIL_1 = "clemencio@upm.es";
    public static final String VALID_EMAIL_2 = "frank.castle@upm.es";
    public static final String VALID_EMAIL_3 = "clemencio.morales.lucas@upm.es";
    public static final String VALID_EMAIL_4 = "transport@gov.co.uk";
    public static final String VALID_EMAIL_5 = "somebody.cool@gmail.com";
    public static final String VALID_EMAIL_6 = "anyone@192.168.0.1";

    public static final String INVALID_EMAIL_1 = "Frank Castle";
    public static final String INVALID_EMAIL_2 = "mail@x";
    public static final String INVALID_EMAIL_3 = "fake.email@332#";
    public static final String INVALID_EMAIL_4 = "@upm.es";
    public static final String INVALID_EMAIL_5 = "clemen@upm .es";
    public static final String INVALID_EMAIL_6 = "clemen @upm.es";

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
    public static final String INVALID_PASSWORD_6 = "   password123@";

    /*@Rule
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

    @Test
    public void setValidPassword1DoesNotThrowException(){
        this.email.setPassword(VALID_PASSWORD_1);
    }

    @Test
    public void setValidPassword2DoesNotThrowException(){
        this.email.setPassword(VALID_PASSWORD_2);
    }

    @Test
    public void setValidPassword3DoesNotThrowException(){
        this.email.setPassword(VALID_PASSWORD_3);
    }

    @Test
    public void setValidPassword4DoesNotThrowException(){
        this.email.setPassword(VALID_PASSWORD_4);
    }

    @Test
    public void setValidPassword5DoesNotThrowException(){
        this.email.setPassword(VALID_PASSWORD_5);
    }

    @Test
    public void setValidPassword6DoesNotThrowException(){
        this.email.setPassword(VALID_PASSWORD_6);
    }

    @Test
    public void setInvalidPassword1ThrowsException(){
        getReadyForException(Email.PASSWORD_NOT_VALID);
        this.email.setPassword(INVALID_PASSWORD_1);
    }

    @Test
    public void setInvalidPassword2ThrowsException(){
        getReadyForException(Email.PASSWORD_NOT_VALID);
        this.email.setPassword(INVALID_PASSWORD_2);
    }

    @Test
    public void setInvalidPassword3ThrowsException(){
        getReadyForException(Email.PASSWORD_NOT_VALID);
        this.email.setPassword(INVALID_PASSWORD_3);
    }

    @Test
    public void setInvalidPassword4ThrowsException(){
        getReadyForException(Email.PASSWORD_NOT_VALID);
        this.email.setPassword(INVALID_PASSWORD_4);
    }

    @Test
    public void setInvalidPassword5ThrowsException(){
        getReadyForException(Email.PASSWORD_NOT_VALID);
        this.email.setPassword(INVALID_PASSWORD_5);
    }

    @Test
    public void setInvalidPassword6ThrowsException(){
        getReadyForException(Email.PASSWORD_NOT_VALID);
        this.email.setPassword(INVALID_PASSWORD_6);
    }

    public void getReadyForException(final String message) {
        exception.expect(IllegalArgumentException.class);
        exception.expectMessage(message);
    }*/
}
