package com.example.project.spring.batch.model;


public record PersonWriterModel(

        Long id ,

        String name,

        String email,

        int age,

        String cpf,

        String gender

) {
}
