package com.saviorru.comsserver.model;

import java.time.LocalDate;
import java.time.Period;
import java.util.Optional;


public class Player {

    private String firstName;
    private String lastName;
    private String patronymicName;

    private LocalDate birthDate;

    public Player(String firstName, String lastName, LocalDate birthDate) throws Exception {
        if (firstName == null || lastName == null || birthDate == null) throw new NullPointerException();
        if (firstName == "" || lastName == "") throw new Exception("Name of surname can't be empty");
        this.firstName = firstName;
        this.lastName = lastName;
        this.birthDate = birthDate;
    }

    public Player(String firstName, String lastName, String patronymicName, LocalDate birthDate) throws Exception {
        if (firstName == null || lastName == null || birthDate == null) throw new NullPointerException();
        if (firstName == "" || lastName == "") throw new Exception("Name of surname can't be empty");
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

    public String getPatronymicName() {
        return (patronymicName == null) ? "" : patronymicName;
    }

    private int calculateAge(LocalDate birthDate) {
        return Period.between(birthDate, LocalDate.now()).getYears();
    }

    public int getAge() {
        return calculateAge(this.birthDate);
    }
}
