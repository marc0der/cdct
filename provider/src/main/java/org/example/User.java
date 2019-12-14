package org.example;

import java.util.List;
import java.util.UUID;

public class User {
    private final UUID ssn;
    private final String firstName;
    private final String lastName;
    private final Integer age;
    private final List<Id> ids;

    public User(UUID ssn, String firstName, String lastName, Integer age, List<Id> ids) {
        this.ssn = ssn;
        this.firstName = firstName;
        this.lastName = lastName;
        this.age = age;
        this.ids = ids;
    }

    public UUID getSsn() {
        return ssn;
    }

    public String getFirstName() {
        return firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public Integer getAge() {
        return age;
    }

    public List<Id> getIds() {
        return ids;
    }
}
