package com.tms.todolist.dto;

import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;

@Value
@Builder
public class RoomDtoReq {
    Date created;
    Date updated;
    boolean active;
    String name;
    List<Long> users;
    List<Long> tasks;
}
