package com.taskmind.api.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import com.taskmind.api.domain.DuplicateTaskException;
import com.taskmind.api.domain.Task;
import java.util.List;
import org.junit.jupiter.api.Test;

class TaskServiceTest {

    private static Task t(int id, int priority, boolean completed) {
        return new Task(id, "Tarefa " + id, priority, completed);
    }

    @Test
    void listaNaOrdemDeCadastro() {
        TaskService service = new TaskService();
        service.add(t(2, 1, false));
        service.add(t(1, 5, false));
        assertEquals(List.of(2, 1), service.list().stream().map(Task::id).toList());
    }

    @Test
    void idDuplicadoEhRejeitadoEOEstadoNaoMuda() {
        TaskService service = new TaskService();
        service.add(t(1, 3, false));
        assertThrows(DuplicateTaskException.class, () -> service.add(t(1, 5, true)));
        assertEquals(List.of(t(1, 3, false)), service.list());
    }

    @Test
    void pendentesSaemPorPrioridadeDecrescente() {
        TaskService service = new TaskService();
        service.add(t(101, 3, false));
        service.add(t(102, 5, false));
        service.add(t(103, 2, true));
        service.add(t(104, 5, false));
        service.add(t(105, 1, false));
        assertEquals(List.of(102, 104, 101, 105), service.pendingIds());
    }

    @Test
    void clearRemoveTudo() {
        TaskService service = new TaskService();
        service.add(t(1, 3, false));
        service.clear();
        assertEquals(List.of(), service.list());
        assertEquals(List.of(), service.pendingIds());
    }
}
