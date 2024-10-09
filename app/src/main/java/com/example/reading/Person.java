package com.example.reading;

public class Person {
    public String firstName, lastName, phone;
    public Gender gender;

    public Person(String firstName, String lastName, String phone, Gender gender) {
        this.firstName = firstName;
        this.lastName = lastName;
        this.phone = phone;
        this.gender = gender;

    }

    public Person() {
        this.firstName = null;
        this.lastName = null;
        this.phone = null;
        this.gender = null;
    }

    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getPhone() {
        return phone;
    }

    public void setPhone(String phone) {
        this.phone = phone;
    }

    public Gender getGender() {
        return gender;
    }

    public void setGender(Gender gender) {
        this.gender = gender;
    }
}
