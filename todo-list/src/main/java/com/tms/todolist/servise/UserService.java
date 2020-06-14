package com.tms.todolist.servise;

import com.tms.todolist.model.User;

import java.util.List;

public interface UserService {
    User register(User user); //регистрация user

    List<User> getAll(); // выводит список всех пользователей

    User findByUsername(String username); //поиск по имени

    User findById(Long id); // поиск по id

    void delete (Long id); //удаление по id




}
