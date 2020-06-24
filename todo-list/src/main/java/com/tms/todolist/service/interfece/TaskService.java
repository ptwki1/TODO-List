package com.tms.todolist.service.interfece;

import com.tms.todolist.model.Task;
import com.tms.todolist.model.User;

import java.util.List;

public interface TaskService {
    Task addTask(Task task);

    void delete(Long id);

    Task findById(Long id);

    Task editUser(Task task);

    List<Task> getAll();

    List<Task> getAllActive();
}
