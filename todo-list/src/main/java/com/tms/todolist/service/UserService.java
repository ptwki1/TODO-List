package com.tms.todolist.service;

import com.tms.todolist.dto.TaskDto;
import com.tms.todolist.dto.TaskDtoReq;
import com.tms.todolist.dto.UserDto;
import com.tms.todolist.dto.UserDtoReq;

import java.util.List;

public interface UserService {
    UserDto addUser(UserDtoReq userDtoReq);

    void delete(Long id);//?

    UserDto  findById(Long id);

    UserDto editTask(Long id,UserDtoReq userDtoReq);

    List<UserDto> findByName(String name);
}