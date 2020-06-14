package com.tms.todolist.servise.impl;

import com.tms.todolist.model.Role;
import com.tms.todolist.model.Status;
import com.tms.todolist.model.User;
import com.tms.todolist.repository.RoleRepository;
import com.tms.todolist.repository.UserRepository;
import com.tms.todolist.servise.UserService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.bcrypt.BCryptPasswordEncoder;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
@Service
public class UserServiceImpl implements UserService {

    private final UserRepository userRepository;
    private final RoleRepository roleRepository;
    private final BCryptPasswordEncoder passwordEncoder;


    @Autowired
    public UserServiceImpl(UserRepository userRepository, RoleRepository roleRepository, BCryptPasswordEncoder passwordEncoder) {
        this.userRepository = userRepository;
        this.roleRepository = roleRepository;
        this.passwordEncoder = passwordEncoder;
    }

    @Override
    public User register(User user) {
        Role roleUser=roleRepository.findByName("ROLE_USER");   //при регистрации присваиваем все новым пользователям роль
        List<Role> userRoles=new ArrayList<>();
        userRoles.add(roleUser);


        user.setPassword(passwordEncoder.encode(user.getPassword())); //зашифровываем,чтобы не хранить оригинал в бд
        user.setRoles(userRoles);
        user.setActive(Status.ACTIVE);

        return userRepository.save(user); // сохраняем нового пользователя
    }

    @Override
    public List<User> getAll() {
        return userRepository.findAll(); //получение списка всех user
    }

    @Override
    public User findByUsername(String username) {
        return userRepository.findByUsername(username); // получение пользователя по username
    }

    @Override
    public User findById(Long id) {
        return userRepository.findById(id).orElse(null); //поиск по id пользователя иначе null
    }

    @Override
    public void delete(Long id) {
        userRepository.deleteById(id);
    }
}
