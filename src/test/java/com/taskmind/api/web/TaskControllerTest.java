package com.taskmind.api.web;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import com.taskmind.api.service.TaskService;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.ResultActions;

@SpringBootTest
@AutoConfigureMockMvc
class TaskControllerTest {

    @Autowired
    private MockMvc mvc;

    @Autowired
    private TaskService service;

    @BeforeEach
    void limparEstado() {
        service.clear();
    }

    private ResultActions postTask(String json) throws Exception {
        return mvc.perform(post("/api/tasks").contentType(MediaType.APPLICATION_JSON).content(json));
    }

    private static String task(int id, int priority, boolean completed) {
        return "{\"id\":" + id + ",\"title\":\"Tarefa " + id + "\",\"priority\":" + priority
                + ",\"completed\":" + completed + "}";
    }

    @Test
    void healthResponde() throws Exception {
        mvc.perform(get("/api/health")).andExpect(status().isOk()).andExpect(jsonPath("$.status").value("ok"));
    }

    @Test
    void criaTarefaEDevolve201() throws Exception {
        postTask(task(1, 3, false))
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.id").value(1))
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void completedOmitidoVaiParaFalso() throws Exception {
        postTask("{\"id\":2,\"title\":\"x\",\"priority\":2}")
                .andExpect(status().isCreated())
                .andExpect(jsonPath("$.completed").value(false));
    }

    @Test
    void prioridadeInvalidaOuCampoAusenteDevolve422() throws Exception {
        postTask(task(1, 9, false)).andExpect(status().isUnprocessableEntity())
                .andExpect(jsonPath("$.error").value("ValidationError"));
        postTask("{\"title\":\"sem id\",\"priority\":3}").andExpect(status().isUnprocessableEntity());
        postTask("{\"id\":3,\"title\":\"  \",\"priority\":3}").andExpect(status().isUnprocessableEntity());
    }

    @Test
    void idDuplicadoDevolve409() throws Exception {
        postTask(task(1, 3, false)).andExpect(status().isCreated());
        postTask(task(1, 4, false)).andExpect(status().isConflict())
                .andExpect(jsonPath("$.error").value("DuplicateTaskException"));
    }

    @Test
    void pendentesSaemOrdenadosComoNoEnunciado() throws Exception {
        postTask(task(101, 3, false));
        postTask(task(102, 5, false));
        postTask(task(103, 2, true));
        postTask(task(104, 5, false));
        postTask(task(105, 1, false));
        mvc.perform(get("/api/tasks/pending"))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.length()").value(4))
                .andExpect(jsonPath("$[0]").value(102))
                .andExpect(jsonPath("$[1]").value(104))
                .andExpect(jsonPath("$[2]").value(101))
                .andExpect(jsonPath("$[3]").value(105));
    }
}
