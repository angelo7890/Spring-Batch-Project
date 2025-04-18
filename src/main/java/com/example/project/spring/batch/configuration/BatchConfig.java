package com.example.project.spring.batch.configuration;


import com.example.project.spring.batch.model.PersonReaderModel;
import com.example.project.spring.batch.model.PersonWriterModel;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.job.builder.JobBuilder;
import org.springframework.batch.core.repository.JobRepository;
import org.springframework.batch.core.step.builder.StepBuilder;
import org.springframework.batch.item.ItemProcessor;
import org.springframework.batch.item.ItemReader;
import org.springframework.batch.item.ItemWriter;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.transaction.PlatformTransactionManager;

@Configuration
public class BatchConfig {
    private final PlatformTransactionManager transactionManager;
    private final JobRepository jobRepository;


    public BatchConfig(PlatformTransactionManager transactionManager, JobRepository jobRepository) {
        this.transactionManager = transactionManager;
        this.jobRepository = jobRepository;
    }

    @Bean
    public Job job(Step step) {
        return new JobBuilder("job", jobRepository)
                .start(step).build();
    }

    @Bean
    public Step step(ItemReader<PersonReaderModel> reader, ItemProcessor<PersonReaderModel, PersonWriterModel> processor , ItemWriter<PersonWriterModel> writer) {
        return new StepBuilder("step", jobRepository)
                .<PersonReaderModel, PersonWriterModel>chunk(100, transactionManager)
                .reader(reader).processor(processor).writer(writer).build();

    }
}
