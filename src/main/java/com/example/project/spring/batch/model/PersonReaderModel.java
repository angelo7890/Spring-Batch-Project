package com.example.project.spring.batch.model;



public record PersonReaderModel(
        String name,

        String email,

        Integer age,

        String cpf,

        String sex
) {
}
