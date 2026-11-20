package com.taskmind.api.domain;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import org.junit.jupiter.api.Test;

class TaskTest {

    @Test
    void prioridadeForaDoIntervaloEhRejeitada() {
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "a", 0, false));
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "a", 6, false));
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "a", -3, false));
    }

    @Test
    void limitesDoIntervaloSaoValidos() {
        assertEquals(1, new Task(1, "a", 1, false).priority());
        assertEquals(5, new Task(1, "a", 5, true).priority());
    }

    @Test
    void tituloNuloOuEmBrancoEhRejeitado() {
        assertThrows(IllegalArgumentException.class, () -> new Task(1, null, 3, false));
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "", 3, false));
        assertThrows(IllegalArgumentException.class, () -> new Task(1, "   ", 3, false));
    }
}
