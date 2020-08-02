package com.tms.todolist.service.security;

import com.tms.todolist.model.Role;
import com.tms.todolist.model.User;
import com.tms.todolist.model.userDetails.MyUserPrincipal;
import com.tms.todolist.repository.UserRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.persistence.EntityNotFoundException;
import javax.transaction.Transactional;
import java.util.stream.Collectors;

@Service
@Transactional
public class MyUserDetailsService implements UserDetailsService {

    private final UserRepository userRepository;

    @Autowired
    public MyUserDetailsService(UserRepository userRepository) {
        this.userRepository = userRepository;
    }

    @Override
    public UserDetails loadUserByUsername(String login) throws UsernameNotFoundException {
        User user = userRepository.findByLogin(login);
        if (user == null) {
            throw new EntityNotFoundException("not found User with login " + login);
        }
        return MyUserPrincipal.builder()
                .password(user.getPassword())
                .username(user.getLogin())
                .roles(user.getRoles().stream().map(role -> "ROLE_" + role.getName()).collect(Collectors.toList()))
                .build();
    }
}
