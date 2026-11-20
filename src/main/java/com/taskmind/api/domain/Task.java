package com.taskmind.api.domain;

/**
 * Tarefa. Imutável e sempre válida: a validação acontece na construção.
 *
 * @param id        identificador inteiro
 * @param title     nome da tarefa (não vazio)
 * @param priority  prioridade de 1 (menor) a 5 (maior)
 * @param completed indica se a tarefa foi concluída
 */
public record Task(int id, String title, int priority, boolean completed) {

    public static final int MIN_PRIORITY = 1;
    public static final int MAX_PRIORITY = 5;

    public Task {
        if (title == null || title.isBlank()) {
            throw new IllegalArgumentException("title não pode ser vazio");
        }
        if (priority < MIN_PRIORITY || priority > MAX_PRIORITY) {
            throw new IllegalArgumentException(
                    "priority deve estar entre " + MIN_PRIORITY + " e " + MAX_PRIORITY + " (recebido " + priority + ")");
        }
    }
}
