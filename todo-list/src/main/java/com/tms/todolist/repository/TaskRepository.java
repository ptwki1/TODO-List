package com.tms.todolist.repository;

import com.tms.todolist.model.Task;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface TaskRepository extends JpaRepository<Task,Long> {
    List<Task> findTasksByActive(Boolean active);

    Task findTaskById(Long id);

    @Query("SELECT t FROM Task t JOIN User u ON t.id = :taskId AND u.id = :userId WHERE t.id = :taskId")
    Optional<Task> findByTaskIdAndUser(Long taskId, Long userId);
}
