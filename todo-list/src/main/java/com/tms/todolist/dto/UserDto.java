package com.tms.todolist.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class UserDto {
    Long id;
    Date created;
    Date updated;
    boolean active;
    String name;
    String avatar;
    String login;
    String password;
    List<TaskShortDto> tasks;
    List<RoleShortDto> roles;

}
