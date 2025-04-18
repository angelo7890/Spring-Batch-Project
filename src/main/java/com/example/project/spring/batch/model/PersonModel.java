package com.example.project.spring.batch.model;



public class PersonModel {

    private String name;

    private String email;

    private Integer age;

    private String cpf;

    private String sexo;

    public PersonModel() {
    }

    public PersonModel(String name, String email, Integer age, String cpf, String sexo) {
        this.name = name;
        this.email = email;
        this.age = age;
        this.cpf = cpf;
        this.sexo = sexo;
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

    public Integer getAge() {
        return age;
    }

    public void setAge(Integer age) {
        this.age = age;
    }

    public String getCpf() {
        return cpf;
    }

    public void setCpf(String cpf) {
        this.cpf = cpf;
    }

    public String getSexo() {
        return sexo;
    }

    public void setSexo(String sexo) {
        this.sexo = sexo;
    }
}
