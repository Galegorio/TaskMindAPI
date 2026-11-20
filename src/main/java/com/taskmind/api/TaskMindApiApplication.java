package com.taskmind.api;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/** Ponto de entrada da API de tarefas do TaskMind. */
@SpringBootApplication
public class TaskMindApiApplication {

    public static void main(String[] args) {
        SpringApplication.run(TaskMindApiApplication.class, args);
    }
}
