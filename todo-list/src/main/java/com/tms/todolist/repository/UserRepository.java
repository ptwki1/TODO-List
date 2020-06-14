package com.tms.todolist.repository;

import com.tms.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username); //Найти пользователя по его имени



}
