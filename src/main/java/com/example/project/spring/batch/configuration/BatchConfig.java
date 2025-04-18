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
import org.springframework.batch.item.database.JdbcBatchItemWriter;
import org.springframework.batch.item.database.builder.JdbcBatchItemWriterBuilder;
import org.springframework.batch.item.file.FlatFileItemReader;
import org.springframework.batch.item.file.mapping.BeanWrapperFieldSetMapper;
import org.springframework.batch.item.file.mapping.DefaultLineMapper;
import org.springframework.batch.item.file.transform.DelimitedLineTokenizer;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.io.ClassPathResource;
import org.springframework.core.io.FileSystemResource;
import org.springframework.transaction.PlatformTransactionManager;

import javax.sql.DataSource;

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
                .reader(reader)
                .processor(processor)
                .writer(writer)
                .build();

    }

    @Bean
    public FlatFileItemReader<PersonReaderModel> reader() {
        FlatFileItemReader<PersonReaderModel> reader = new FlatFileItemReader<>();
        reader.setResource(new FileSystemResource("files/pessoas.csv"));
        reader.setLineMapper(new DefaultLineMapper<PersonReaderModel>() {{
            setLineTokenizer(new DelimitedLineTokenizer() {{
                setNames("name", "email", "age", "cpf", "gender");
            }});
            setFieldSetMapper(new BeanWrapperFieldSetMapper<>() {{
                setTargetType(PersonReaderModel.class);
            }});
        }});
        return reader;
    }

    @Bean
    public ItemProcessor<PersonReaderModel, PersonWriterModel> processor() {
        return person ->{
            return new PersonWriterModel(
                   null,
                   person.getName(),
                   person.getEmail(),
                   person.getAge(),
                   person.getCpf(),
                   person.getGender()
           );
        };
    }


    @Bean
    public JdbcBatchItemWriter<PersonWriterModel> writer(DataSource db) {
        return new JdbcBatchItemWriterBuilder<PersonWriterModel>()
                .dataSource(db)
                .sql("""
                    INSERT INTO pessoa ("name", email, age, cpf, gender)
                    VALUES (:name, :email, :age, :cpf, :gender)
                """)
                .beanMapped()
                .build();
    }
}
