package com.taskmind.api.web;

import com.taskmind.api.domain.Task;
import com.taskmind.api.service.TaskService;
import jakarta.validation.Valid;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/api")
public class TaskController {

    private final TaskService service;

    public TaskController(TaskService service) {
        this.service = service;
    }

    @GetMapping("/health")
    public Map<String, String> health() {
        return Map.of("status", "ok");
    }

    @PostMapping("/tasks")
    @ResponseStatus(HttpStatus.CREATED)
    public Task create(@Valid @RequestBody TaskRequest request) {
        return service.add(request.toTask());
    }

    @GetMapping("/tasks")
    public List<Task> list() {
        return service.list();
    }

    /** IDs das tarefas pendentes, da maior para a menor prioridade (empates na ordem de cadastro). */
    @GetMapping("/tasks/pending")
    public List<Integer> pending() {
        return service.pendingIds();
    }
}
