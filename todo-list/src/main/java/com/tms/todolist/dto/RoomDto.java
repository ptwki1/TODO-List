package com.tms.todolist.dto;

import com.tms.todolist.model.Task;
import com.tms.todolist.model.User;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;
@Value
@Builder
public class RoomDto {
    Long id;
    Date created;
    Date updated;
    boolean active;
    String name;
    List<UserShortDto> users;
    List<TaskShortDto> tasks;
    
}
