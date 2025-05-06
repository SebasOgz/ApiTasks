package com.pruebaTecProte.ejercicioPrueba.repository;

import com.pruebaTecProte.ejercicioPrueba.model.Task;
import com.pruebaTecProte.ejercicioPrueba.model.TaskStatus;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task, Long> {

    List<Task> findByAssignedTo(String assignedTo);
    List<Task> findByStatus(TaskStatus status);
    List<Task> findByAssignedToAndStatus(String assignedTo, TaskStatus status);

}
