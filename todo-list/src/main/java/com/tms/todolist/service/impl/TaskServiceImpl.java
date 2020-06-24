package com.tms.todolist.service.impl;

import com.tms.todolist.model.Task;
import com.tms.todolist.repository.TaskRepository;
import com.tms.todolist.service.interfece.TaskService;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class TaskServiceImpl implements TaskService {

    private final TaskRepository repo;

    @Autowired
    public TaskServiceImpl(TaskRepository repo) {
        this.repo = repo;
    }

    @Override
    public Task addTask(Task task) {
        return repo.saveAndFlush(task);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public Task findById(Long id) {
        return repo.findTaskById(id);
    }

    @Override
    public Task editUser(Task task) {
        return repo.saveAndFlush(task);
    }

    @Override
    public List<Task> getAll() {
        return repo.findAll();
    }

    @Override
    public List<Task> getAllActive() {
        return repo.findTasksByActive();
    }
}
