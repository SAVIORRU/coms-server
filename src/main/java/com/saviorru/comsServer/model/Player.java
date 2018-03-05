package com.saviorru.comsServer.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


public class Player {

    private String firstName;
    private String lastName;
    private Optional<String> patronymicName;

    private LocalDate birthDate;

    public Player(String firstName, String lastName, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }
    public Player(String firstName, String lastName, Optional<String> patronymicName, LocalDate birthDate){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.birthDate = birthDate;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public  String getPatronymicName() {
        return patronymicName.orElse(" ");
    }

    private int calculateAge(LocalDate birthdate){
        return Period.between(birthdate,LocalDate.now()).getYears();
    }

    public int getAge() {
        return calculateAge(this.birthDate);
    }
}
