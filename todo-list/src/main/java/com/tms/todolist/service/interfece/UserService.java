package com.tms.todolist.service.interfece;

import com.tms.todolist.model.User;

import java.util.List;

public interface UserService {
    User addUser(User user);

    void delete(Long id);

    User getByUsername(String username);

    User editUser(User User);

    List<User> getAll();
}
