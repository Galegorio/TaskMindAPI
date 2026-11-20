package com.taskmind.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.Test;

class PendingTasksTest {

    private static Task t(int id, int priority, boolean completed) {
        return new Task(id, "Tarefa " + id, priority, completed);
    }

    @Test
    void exemploDoEnunciado() {
        List<Task> tasks = List.of(t(101, 3, false), t(102, 5, false), t(103, 2, true), t(104, 5, false),
                t(105, 1, false));
        assertEquals(List.of(102, 104, 101, 105), PendingTasks.ids(tasks));
    }

    @Test
    void listaVaziaDevolveListaVazia() {
        assertEquals(List.of(), PendingTasks.ids(List.of()));
    }

    @Test
    void tarefasConcluidasNuncaAparecem() {
        assertEquals(List.of(), PendingTasks.ids(List.of(t(1, 5, true), t(2, 3, true))));
    }

    @Test
    void empatesMantemAOrdemOriginalMesmoComIdsDesordenados() {
        List<Task> tasks = List.of(t(9, 3, false), t(3, 3, false), t(7, 3, false), t(1, 5, false), t(8, 3, false));
        assertEquals(List.of(1, 9, 3, 7, 8), PendingTasks.ids(tasks));
    }

    @Test
    void naoModificaAListaRecebida() {
        List<Task> original = new ArrayList<>(List.of(t(1, 1, false), t(2, 5, false), t(3, 3, true)));
        List<Task> copia = new ArrayList<>(original);
        PendingTasks.ids(original);
        assertEquals(copia, original);
    }

    @Test
    void resultadoEhImutavel() {
        List<Integer> result = PendingTasks.ids(List.of(t(1, 1, false)));
        assertThrows(UnsupportedOperationException.class, () -> result.add(2));
    }

    @Test
    void listaNulaEhRejeitada() {
        assertThrows(IllegalArgumentException.class, () -> PendingTasks.ids(null));
    }

    @Test
    void elementoNuloEhRejeitado() {
        List<Task> tasks = new ArrayList<>();
        tasks.add(null);
        assertThrows(NullPointerException.class, () -> PendingTasks.ids(tasks));
    }

    @Test
    void coincideComUmOraculoIndependenteEmMuitosDados() {
        Random rng = new Random(7);
        List<Task> tasks = new ArrayList<>();
        for (int i = 0; i < 2000; i++) {
            tasks.add(t(i, 1 + rng.nextInt(5), rng.nextInt(4) == 0));
        }
        List<Integer> esperado = new ArrayList<>();
        for (int prioridade = 5; prioridade >= 1; prioridade--) {   // baldes: ordem original dentro de cada prioridade
            for (Task task : tasks) {
                if (!task.completed() && task.priority() == prioridade) {
                    esperado.add(task.id());
                }
            }
        }
        assertEquals(esperado, PendingTasks.ids(tasks));
    }
}
