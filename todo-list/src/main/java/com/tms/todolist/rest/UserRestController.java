package com.tms.todolist.rest;

import com.tms.todolist.model.User;
import com.tms.todolist.service.impl.TaskServiceImpl;
import com.tms.todolist.service.impl.UserServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
public class UserRestController {

    private final UserServiceImpl userService;

    private final TaskServiceImpl taskService;

    @Autowired
    public UserRestController(UserServiceImpl userService, TaskServiceImpl taskService) {
        this.userService = userService;
        this.taskService = taskService;
    }

    @GetMapping("/user/findUser")
    public User getUsername(String username){
        return userService.getByUsername(username);
    }

    @GetMapping("/user/findAll")
    public List<User> getAllUsers(){
        return userService.getAll();
    }

    @DeleteMapping("/user/deleteUser/{id}")
    public void deleteUser(@PathVariable(value = "id") Long id){
        userService.delete(id);
    }

    @PutMapping("/user/edit/{username}")
    public  User editUser(@PathVariable(value = "username") String username){
        User user=userService.getByUsername(username);
        return  userService.editUser(user);
    }
    @PostMapping("/user")
    public User addNewUser(@RequestBody User user){
        return userService.addUser(user);
    }

}
