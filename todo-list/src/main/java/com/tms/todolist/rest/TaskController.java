package com.tms.todolist.rest;

import com.tms.todolist.dto.TaskDto;
import com.tms.todolist.dto.TaskDtoReq;
import com.tms.todolist.dto.TaskShortDto;
import com.tms.todolist.service.impl.TaskServiceImpl;
import lombok.AllArgsConstructor;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
@CrossOrigin(origins = { "http://localhost:3000"})
@RestController
@AllArgsConstructor
public class TaskController {

    /*
    
    /tasks      - GET
    /tasks/{id} - GET
    /tasks      - POST
    /tasks/{id} - PUT
    /tasks/{id} - DELETE
    ____

    /users      - GET
    /users/{id} - GET
    /users      - POST
    /users/{id} - PUT
    /users/{id} - DELETE

    /users/{id}/tasks      - GET
    /users/{id}/tasks/{id} - GET

    /users/{id} - GET
    /users      - POST
    /users/{id} - PUT
    /users/{id} - DELETE
    
     */
    
    private final TaskServiceImpl taskService;

    @GetMapping("/task/getAll")
    public List<TaskDto> getAllTask() {
        return taskService.getAll();
    }

    @DeleteMapping("/task/deleteTask/{id}")
    public void deleteTask(@PathVariable Long id) {
        taskService.delete(id);
    }

    @PutMapping("/task/edit/{id}")
    public TaskDto editTask(@PathVariable(value = "id") Long id, @RequestBody TaskDtoReq task) {
        return taskService.editTask(id, task);
    }

    @PostMapping("/task/addTask")
    public TaskDto addTask(@RequestBody @Validated TaskDtoReq task) {
        return taskService.addTask(task);
    }

    @Deprecated
    @GetMapping("/users/{userId}/tasks/{taskId}")
    public TaskDto one(@PathVariable final Long userId, @PathVariable final Long taskId) {
        return taskService.findById(userId, taskId);
    }

    @GetMapping("/task/get/{id}")
    public TaskDto getById(@PathVariable(value = "id") Long id) {
        return taskService.findById(id);
    }

    // /tasks/active
    @GetMapping("/task/getAllActive")
    public List<TaskShortDto> getAllActive() {
        return taskService.allActive();
    }

    // /tasks/{id}/finish
    @PostMapping("/task/done/{id}")
    public TaskDto doTask(@PathVariable final Long id) {
        return taskService.doneTask(id);
    }

}
