package com.tms.todolist.rest;

import com.tms.todolist.dto.UserDto;
import com.tms.todolist.dto.UserDtoReq;
import com.tms.todolist.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserController {
    private final UserServiceImpl userService;

    @Autowired
    public UserController(UserServiceImpl userService) {
        this.userService = userService;
    }

    @PostMapping("/user/addUser")
    public UserDto addUser(@RequestBody @Validated UserDtoReq userDtoReq) {
        return userService.addUser(userDtoReq);
    }

    @DeleteMapping("/user/deleteUser/{id}")
    public void deleteUser(@PathVariable Long id) {
        userService.delete(id);
    }

    @PutMapping("/user/editUser/{id}")
    public UserDto editUser(@PathVariable Long id, @RequestBody UserDtoReq user) {
        return userService.editTask(id, user);
    }

    @GetMapping("/user/getUserById/{id}")
    public UserDto getById(@PathVariable Long id) {
        return userService.findById(id);
    }

    @GetMapping("/user/getUsersByLogin/{name}")
    public List<UserDto> findAllByPartName(@PathVariable String name) {
        return userService.findByName(name);
    }
}