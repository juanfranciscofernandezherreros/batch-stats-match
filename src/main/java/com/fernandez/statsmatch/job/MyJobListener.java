package com.fernandez.statsmatch.job;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobExecutionListener;
import org.springframework.stereotype.Component;

@Component
public class MyJobListener implements JobExecutionListener {

    @Override
    public void beforeJob(JobExecution jobExecution) {
        // Puedes realizar acciones antes de que el job comience
        System.out.println("Before job starts...");
    }

    @Override
    public void afterJob(JobExecution jobExecution) {
        if (jobExecution.getStatus() == BatchStatus.COMPLETED) {
            // Acciones después de que el trabajo se complete con éxito
        	 System.out.println("Job completed successfully. Closing resources...");
        	     
            // Realizar acciones de cierre o limpieza aquí
        } else if (jobExecution.getStatus() == BatchStatus.FAILED) {
            // Acciones después de que el trabajo falle
        	 System.out.println("Job failed. Closing resources...");
            // Realizar acciones de cierre o limpieza aquí
        }
    }
}

