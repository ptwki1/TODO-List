package com.tms.todolist.repository;

import com.tms.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

public interface UserRepository extends JpaRepository<User,Long> {
    User findByUsername(String username);
}
