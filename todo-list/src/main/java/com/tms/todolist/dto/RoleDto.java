package com.tms.todolist.dto;

import com.tms.todolist.model.User;
import lombok.Builder;
import lombok.Value;

import java.util.List;
@Value
@Builder
public class RoleDto {
    Long id;
    String name;
    List<User> user;
}
