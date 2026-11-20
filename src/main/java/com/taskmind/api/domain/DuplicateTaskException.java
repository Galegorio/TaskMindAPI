package com.taskmind.api.domain;

/** Já existe uma tarefa com o mesmo ID. */
public class DuplicateTaskException extends RuntimeException {

    public DuplicateTaskException(int id) {
        super("já existe uma tarefa com id " + id);
    }
}
