package com.tms.todolist.repository;

import com.tms.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
@Repository
public interface TaskRepository extends JpaRepository<Task,Long > {

    List<Task> findTasksByActive(Boolean active);

    Task findTaskById(Long id);
}
