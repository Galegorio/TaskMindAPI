package com.taskmind.api.domain;

import java.util.Comparator;
import java.util.List;
import java.util.Objects;

/** Regra de negócio central: fila de tarefas pendentes. Sem dependências de framework. */
public final class PendingTasks {

    private PendingTasks() {
    }

    /**
     * IDs das tarefas pendentes, da maior para a menor prioridade.
     *
     * <p>Empates mantêm a ordem original da lista: {@code Stream.sorted()} é estável em streams ordenados, e um
     * comparador invertido não inverte empates. A lista recebida não é modificada; o resultado é imutável.
     * Complexidade: O(n log n) em tempo e O(n) em espaço.
     *
     * @throws IllegalArgumentException se a lista for nula
     * @throws NullPointerException     se a lista contiver um elemento nulo
     */
    public static List<Integer> ids(List<Task> tasks) {
        if (tasks == null) {
            throw new IllegalArgumentException("a lista de tarefas não pode ser nula");
        }
        return tasks.stream()
                .map(t -> Objects.requireNonNull(t, "a lista contém uma tarefa nula"))
                .filter(t -> !t.completed())
                .sorted(Comparator.comparingInt(Task::priority).reversed())
                .map(Task::id)
                .toList();
    }
}
