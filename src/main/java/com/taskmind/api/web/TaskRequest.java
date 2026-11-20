package com.taskmind.api.web;

import com.taskmind.api.domain.Task;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

/** Corpo do POST /api/tasks. {@code completed} é opcional (padrão: falso). */
public record TaskRequest(
        @NotNull Integer id,
        @NotBlank String title,
        @NotNull @Min(1) @Max(5) Integer priority,
        Boolean completed) {

    public Task toTask() {
        return new Task(id, title, priority, Boolean.TRUE.equals(completed));
    }
}
