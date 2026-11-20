package com.taskmind.api.service;

import com.taskmind.api.domain.DuplicateTaskException;
import com.taskmind.api.domain.PendingTasks;
import com.taskmind.api.domain.Task;
import java.util.ArrayList;
import java.util.LinkedHashMap;
import java.util.List;
import java.util.Map;
import org.springframework.stereotype.Service;

/** Armazenamento de tarefas em memória (thread-safe) e consultas. Troque por um repositório JPA quando precisar persistir. */
@Service
public class TaskService {

    private final Map<Integer, Task> tasks = new LinkedHashMap<>();

    /** Cadastra a tarefa. IDs repetidos levantam {@link DuplicateTaskException}. */
    public synchronized Task add(Task task) {
        if (tasks.containsKey(task.id())) {
            throw new DuplicateTaskException(task.id());
        }
        tasks.put(task.id(), task);
        return task;
    }

    /** Todas as tarefas, na ordem de cadastro. */
    public synchronized List<Task> list() {
        return List.copyOf(tasks.values());
    }

    /** IDs das tarefas pendentes por prioridade decrescente. */
    public synchronized List<Integer> pendingIds() {
        return PendingTasks.ids(new ArrayList<>(tasks.values()));
    }

    /** Remove todas as tarefas (útil em testes). */
    public synchronized void clear() {
        tasks.clear();
    }
}
