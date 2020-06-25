package com.tms.todolist.service.impl;

import com.tms.todolist.model.User;
import com.tms.todolist.repository.UserRepository;
import com.tms.todolist.service.interfece.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import javax.transaction.Transactional;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private  final UserRepository repo;
    @Autowired
    public UserServiceImpl(UserRepository repo) {
        this.repo = repo;
    }

    @Override
    public User addUser(User user) {
        return repo.saveAndFlush(user);
    }

    @Override
    public void delete(Long id) {
        repo.deleteById(id);
    }

    @Override
    public User getByUsername(String username) {
        return repo.findByUsername(username);
    }

    @Override
    public User editUser(User user) {
        return repo.saveAndFlush(user);
    }

    @Override
    public List<User> getAll() {
        return repo.findAll();
    }
}
