package com.saviorru.comsserver.model;

import java.util.Optional;


public class Player {

    private String firstName;
    private String lastName;
    private Optional<String> patronymicName;

    private Integer age;

    public Player(String firstName, String lastName, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
    }
    Player(String firstName, String lastName, Optional<String> patronymicName, Integer age){
        this.firstName = firstName;
        this.lastName = lastName;
        this.patronymicName = patronymicName;
        this.age = age;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public  String getPatronymicName() {
        return patronymicName.orElse(" ");
        //(patronymicName != null) ? patronymicName:" ";
    }

    public Integer getAge() {
        return age;
    }
}
