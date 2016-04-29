package com.phd.lucas.morales.clemencio.caronte.domain;

import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

/**
 * Created by Clemencio Morales Lucas on 31/03/2016.
 */

@Data
@NoArgsConstructor
@ToString
public class User {

    @Getter @Setter private Email email;
    @Getter @Setter private Password password;
    @Getter @Setter private String gender;
    @Getter @Setter private int age;
    @Getter @Setter private String ethnicOrigin;
    @Getter @Setter private String maritalStatus;
    @Getter @Setter private String educationLevel;
    @Getter @Setter private String workingSituation;
    @Getter @Setter private String salaryPerYear;
    @Getter @Setter private String disabilityLevel;
}
