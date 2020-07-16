package com.tms.todolist.service.mapper;

import com.tms.todolist.dto.TaskDto;
import com.tms.todolist.dto.TaskDtoReq;
import com.tms.todolist.dto.TaskShortDto;
import com.tms.todolist.dto.UserShortDto;
import com.tms.todolist.model.Task;
import com.tms.todolist.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class TaskMapper {

    public TaskDto toTaskDto(final Task task) {
        return TaskDto.builder()
                .id(task.getId())
                .active(task.isActive())
                .created(task.getCreated())
                .updated(task.getUpdated())
                .description(task.getDescription())
                .status(task.getStatus())
                .users(Optional.ofNullable(task.getUsers())
                        .orElse(new ArrayList<>()).stream()
                        .map(this::toUserShortDto)
                        .collect(Collectors.toList()))
                .build();
    }

    public TaskShortDto toTaskShortDto(final Task task) {
        return TaskShortDto.builder()
                .id(task.getId())
                .description(task.getDescription())
                .status(task.getStatus())
                .active(task.isActive())
                .build();
    }

    private UserShortDto toUserShortDto(final User user) {
        return UserShortDto.builder()
                .id(user.getId())
                .name(user.getName())
                .login(user.getLogin())
                .active(user.isActive())
                .build();
    }

    public Task fromTaskDtoReq(final TaskDtoReq taskDtoReq, List<User> users) {
        return Task.builder()
                .active(taskDtoReq.isActive())
                .created(taskDtoReq.getCreated())
                .updated(taskDtoReq.getUpdated())
                .description(taskDtoReq.getDescription())
                .status(taskDtoReq.getStatus())
                .users(users)
                .build();
    }

}
