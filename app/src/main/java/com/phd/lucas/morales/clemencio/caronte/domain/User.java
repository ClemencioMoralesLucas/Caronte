package com.phd.lucas.morales.clemencio.caronte.domain;

import lombok.Data;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */

@Data
public class User {

    private Email email;
    private String password; //TODO Probably we need a Password class
    private String gender;
    private int age;
    private String ethnicOrigin;
    private String maritalStatus;
    private String educationLevel;
    private String workingSituation;
    private String salaryPerYear;
    private String disabilityLevel;
}
