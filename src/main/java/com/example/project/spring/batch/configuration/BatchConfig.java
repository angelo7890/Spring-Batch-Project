package com.example.project.spring.batch.configuration;


import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    private final PlatformTransactionManager transactionManager;


    public BatchConfig(PlatformTransactionManager transactionManager) {
        this.transactionManager = transactionManager;
    }
    
}
