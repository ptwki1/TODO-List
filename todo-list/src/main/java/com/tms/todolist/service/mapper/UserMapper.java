package com.tms.todolist.service.mapper;

import com.tms.todolist.dto.*;
import com.tms.todolist.model.Role;
import com.tms.todolist.model.Task;
import com.tms.todolist.model.User;
import org.springframework.stereotype.Component;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Component
public class UserMapper {
    public UserDto toUserDto(final User user) {
        return UserDto.builder()
                .id(user.getId())
                .created(user.getCreated())
                .updated(user.getUpdated())
                .active(user.isActive())
                .name(user.getName())
                .avatar(user.getAvatar())
                .login(user.getLogin())
                .password(user.getPassword())
                .tasks(Optional.ofNullable(user.getTasks())
                        .orElse(new ArrayList<>()).stream()
                        .map(this::toTaskShortDto)
                        .collect(Collectors.toList()))
                .roles(Optional.ofNullable(user.getRoles())
                        .orElse(new ArrayList<>()).stream()
                        .map(this::toRoleShortDto)
                        .collect(Collectors.toList()))
                .build();


    }
    private RoleShortDto toRoleShortDto(final Role role){
        return RoleShortDto.builder()
                .name(role.getName())
                .build();
    }
    private TaskShortDto toTaskShortDto(final Task task) {
        return TaskShortDto.builder()
                .id(task.getId())
                .active(task.isActive())
                .status(task.getStatus())
                .description(task.getDescription())
                .build();
    }
    public User fromUserDtoReq(final UserDtoReq userDtoReq, List<Task> tasks,List<Role> roles){
        return User.builder()
                .active(userDtoReq.isActive())
                .created(userDtoReq.getCreated())
                .updated(userDtoReq.getUpdated())
                .login(userDtoReq.getLogin())
                .avatar(userDtoReq.getAvatar())
                .name(userDtoReq.getName())
                .password(userDtoReq.getPassword())
                .tasks(tasks)
                .roles(roles)
                .build();
    }
}
