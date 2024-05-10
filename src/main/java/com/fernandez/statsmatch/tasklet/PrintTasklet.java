package com.fernandez.statsmatch.tasklet;

import org.springframework.batch.core.StepContribution;
import org.springframework.batch.core.scope.context.ChunkContext;
import org.springframework.batch.core.step.tasklet.Tasklet;
import org.springframework.batch.repeat.RepeatStatus;

public class PrintTasklet implements Tasklet {

    private String message;

    public PrintTasklet(String message) {
        this.message = message;
    }

    @Override
    public RepeatStatus execute(StepContribution contribution, ChunkContext chunkContext) throws Exception {
        // Coloca aquí la lógica de tu tarea
        System.out.println(message);
        return RepeatStatus.FINISHED;
    }
}

