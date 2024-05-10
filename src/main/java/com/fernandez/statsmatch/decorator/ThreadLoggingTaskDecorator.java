package com.fernandez.statsmatch.decorator;

import org.springframework.core.task.TaskDecorator;

public class ThreadLoggingTaskDecorator implements TaskDecorator {

    @Override
    public Runnable decorate(Runnable runnable) {
        return () -> {
            try {
                // Imprimir información sobre el hilo antes de ejecutar la tarea
                System.out.println("Thread ID: " + Thread.currentThread().getId() + ", Thread Name: " + Thread.currentThread().getName());
                
                // Ejecutar la tarea
                runnable.run();
            } finally {
                // Puedes agregar más información o acciones de limpieza aquí si es necesario
            }
        };
    }
}

