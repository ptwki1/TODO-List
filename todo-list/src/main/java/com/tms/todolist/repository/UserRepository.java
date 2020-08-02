package com.tms.todolist.repository;

import com.tms.todolist.model.Task;
import com.tms.todolist.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User,Long> {
    User findUserById(Long id);

    @Query("select s from User s where lower(s.name) like lower(concat('%', :name,'%'))")
    List<User> findByPartName(String name);

    User findByLogin(String login);
}
