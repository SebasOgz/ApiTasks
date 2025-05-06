package com.pruebaTecProte.ejercicioPrueba.service;

import com.pruebaTecProte.ejercicioPrueba.model.Task;
import com.pruebaTecProte.ejercicioPrueba.model.TaskStatus;
import com.pruebaTecProte.ejercicioPrueba.repository.TaskRepository;
import org.springframework.stereotype.Service;
import java.util.*;
import java.util.concurrent.atomic.AtomicLong;
import java.util.stream.Collectors;

@Service
public class ServiceSistema {

    private final TaskRepository taskRepository;

    public ServiceSistema(TaskRepository taskRepository) {
        this.taskRepository = taskRepository;
    }

    public Task createTask(Task task) {
        return taskRepository.save(task);
    }

    public List<Task> getTasks(String user, TaskStatus status, String sortBy) {
        List<Task> tasks;

        if (user != null && status != null) {
            tasks = taskRepository.findByAssignedToAndStatus(user, status);
        } else if (user != null) {
            tasks = taskRepository.findByAssignedTo(user);
        } else if (status != null) {
            tasks = taskRepository.findByStatus(status);
        } else {
            tasks = taskRepository.findAll();
        }

        return sortTasks(tasks, sortBy);
    }

    public Task updateTask(Long id, Task updatedTask) {
        Task existing = taskRepository.findById(id)
                .orElseThrow(() -> new NoSuchElementException("Tarea no encontrada"));

        existing.setTitle(updatedTask.getTitle());
        existing.setDescription(updatedTask.getDescription());
        existing.setAssignedTo(updatedTask.getAssignedTo());
        existing.setDueDate(updatedTask.getDueDate());
        existing.setStatus(updatedTask.getStatus());

        return taskRepository.save(existing);
    }

    private List<Task> sortTasks(List<Task> tasks, String sortBy) {
        if ("dueDate".equals(sortBy)) {
            tasks.sort(Comparator.comparing(Task::getDueDate));
        } else if ("status".equals(sortBy)) {
            tasks.sort(Comparator.comparing(Task::getStatus));
        }
        return tasks;
    }

}
