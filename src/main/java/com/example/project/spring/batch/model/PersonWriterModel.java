package com.example.project.spring.batch.model;

import java.util.Date;

public record PersonWriterModel(

        Long id ,

        String name,

        String email,

        Integer age,

        String cpf,

        String sex,

        Date date

) {
}
