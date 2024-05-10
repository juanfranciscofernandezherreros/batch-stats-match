package com.fernandez.statsmatch.config;

import com.fernandez.statsmatch.model.dto.StatsMatchBean;
import com.fernandez.statsmatch.model.entity.StatsMatch;
import com.fernandez.statsmatch.reader.CsvItemReader;
import com.fernandez.statsmatch.writer.ConsoleItemWriter;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.Step;
import org.springframework.batch.core.configuration.annotation.EnableBatchProcessing;
import org.springframework.batch.core.configuration.annotation.JobBuilderFactory;
import org.springframework.batch.core.configuration.annotation.StepBuilderFactory;
import org.springframework.batch.core.launch.support.RunIdIncrementer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.task.TaskExecutor;
import org.springframework.scheduling.concurrent.ThreadPoolTaskExecutor;

import com.fernandez.statsmatch.decorator.ThreadLoggingTaskDecorator;
import com.fernandez.statsmatch.job.MyJobListener;
import com.fernandez.statsmatch.processor.CsvItemProcessor;

@Configuration
@EnableBatchProcessing
public class BatchConfiguration {

    @Autowired
    private JobBuilderFactory jobBuilderFactory;

    @Autowired
    private StepBuilderFactory stepBuilderFactory;

    @Autowired
    private CsvItemReader csvItemReader;

    @Autowired
    private CsvItemProcessor csvItemProcessor;

    @Autowired
    private ConsoleItemWriter consoleItemWriter;

    @Autowired
    private MyJobListener myJobListener;

    @Value("${batch.taskExecutor.corePoolSize:1}")
    private int corePoolSize;

    @Value("${batch.taskExecutor.maxPoolSize:1}")
    private int maxPoolSize;

    @Value("${batch.taskExecutor.queueCapacity:1}")
    private int queueCapacity;

    @Value("${chunk:1000}")
    private int chunk;

    @Bean
    public Job myJob() {
        return jobBuilderFactory.get("statsMatchJob")
                .incrementer(new RunIdIncrementer())
                .listener(myJobListener)
                .start(myStep())
                .build();
    }

    @Bean
    public Step myStep() {
        return stepBuilderFactory.get("myStepStatsMatch")
                .<StatsMatchBean, StatsMatch>chunk(chunk)
                .reader(csvItemReader)
                .processor(csvItemProcessor)
                .writer(consoleItemWriter)
                .taskExecutor(taskExecutor())
                .build();
    }

    @Bean
    public TaskExecutor taskExecutor() {
        ThreadPoolTaskExecutor taskExecutor = new ThreadPoolTaskExecutor();
        taskExecutor.setCorePoolSize(corePoolSize);
        taskExecutor.setMaxPoolSize(maxPoolSize);
        taskExecutor.setQueueCapacity(queueCapacity);
        taskExecutor.setThreadNamePrefix("my-batch-thread-statsMatch");
        taskExecutor.setTaskDecorator(new ThreadLoggingTaskDecorator());
        return taskExecutor;
    }
}
