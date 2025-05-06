package com.pruebaTecProte.ejercicioPrueba.controller;

import com.pruebaTecProte.ejercicioPrueba.model.Task;
import com.pruebaTecProte.ejercicioPrueba.model.TaskStatus;
import com.pruebaTecProte.ejercicioPrueba.service.ServiceSistema;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.*;

import java.util.List;


@RestController
@RequestMapping("/api/tasks")
public class TaskController {

    private final ServiceSistema taskService;

    public TaskController(ServiceSistema taskService) {
        this.taskService = taskService;
    }

    @PostMapping(value = "/create")
    @ResponseStatus(HttpStatus.CREATED)
    public Task createTask(@RequestBody Task task) {
        return taskService.createTask(task);
    }

    @GetMapping(value = "/view")
    public List<Task> getTasks(
            @RequestParam(required = false) String user,
            @RequestParam(required = false) TaskStatus status,
            @RequestParam(required = false) String sortBy
    ) {
        return taskService.getTasks(user, status, sortBy);
    }

    @PutMapping("/update/{id}")
    public Task updateTask(@PathVariable Long id, @RequestBody Task updatedTask) {
        return taskService.updateTask(id, updatedTask);
    }
}