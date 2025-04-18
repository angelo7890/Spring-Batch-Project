package com.example.project.spring.batch.model;

public class PersonReaderModel {

    private String name;
    private String email;
    private int age;
    private String cpf;
    private String gender;

    public PersonReaderModel() {}

    public PersonReaderModel(String name, String email, int age, String cpf, String gender) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.cpf = cpf;
        this.gender = gender;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getAge() {
        return age;
    }

    public void setAge(int age) {
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }
}
