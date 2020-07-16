package com.tms.todolist.service;

import com.tms.todolist.dto.TaskDto;
import com.tms.todolist.dto.TaskDtoReq;
import com.tms.todolist.dto.TaskShortDto;

import java.util.List;

public interface TaskService {
    TaskDto addTask(TaskDtoReq taskDtoReq);

    void delete(Long id);//?

    TaskDto findById(Long id);

    TaskDto findById(Long userId, Long taskId);

    TaskDto editTask(Long id,TaskDtoReq taskDtoReq);

    List<TaskDto> getAll();

    List<TaskShortDto> allActive();

    TaskDto doneTask(Long id);//  ???

    TaskDto newStatus(Long id,TaskDtoReq taskDtoReq);// ???????
}
