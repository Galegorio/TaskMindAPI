package com.taskmind.api.web;

import com.taskmind.api.domain.DuplicateTaskException;
import java.util.List;
import java.util.Map;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/** Erros como JSON {@code {"error": ..., "message"|"messages": ...}}, no mesmo estilo do TaskMind Logistics. */
@RestControllerAdvice
public class ApiExceptionHandler {

    @ExceptionHandler(DuplicateTaskException.class)
    public ResponseEntity<Map<String, Object>> duplicate(DuplicateTaskException ex) {
        return ResponseEntity.status(HttpStatus.CONFLICT)
                .body(Map.of("error", "DuplicateTaskException", "message", ex.getMessage()));
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String, Object>> invalid(MethodArgumentNotValidException ex) {
        List<String> messages = ex.getBindingResult().getFieldErrors().stream()
                .map(e -> e.getField() + ": " + e.getDefaultMessage())
                .toList();
        return ResponseEntity.status(HttpStatus.UNPROCESSABLE_ENTITY)
                .body(Map.of("error", "ValidationError", "messages", messages));
    }
}
