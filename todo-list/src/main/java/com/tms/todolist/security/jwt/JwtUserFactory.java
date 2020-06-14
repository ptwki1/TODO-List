package com.tms.todolist.security.jwt;

import com.tms.todolist.model.Role;
import com.tms.todolist.model.Status;
import com.tms.todolist.model.User;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

public final class JwtUserFactory  {
    public JwtUserFactory() {
    }
    public static JwtUser create(User user){
        return new JwtUser(
                user.getId(),
                user.getUsername(),
                user.getPassword(),
               mapToGrantedAuthorities(new ArrayList<>(user.getRoles())),
                user.getActive().equals(Status.ACTIVE),
                user.getUpdate()
        );
    }
    private static List<GrantedAuthority> mapToGrantedAuthorities(List<Role> userRoles) {
        return userRoles.stream()
                .map(role ->
                        new SimpleGrantedAuthority(role.getName())
                ).collect(Collectors.toList());
    }
}
