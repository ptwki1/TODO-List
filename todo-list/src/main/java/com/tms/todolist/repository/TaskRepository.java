package com.tms.todolist.repository;

import com.tms.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;

public interface TaskRepository extends JpaRepository<Task,Long > {

    List<Task> findTasksByActive();

    Task findTaskById(Long id);
}
