package com.tms.todolist.dto;

import com.tms.todolist.model.TaskStatus;
import lombok.Builder;
import lombok.Value;

import java.util.Date;
import java.util.List;
@Value
@Builder
public class TaskDtoReq {

    Date created;
    Date updated;
    boolean active;
    String description;
    TaskStatus status;
    List<Long> userIds;
}
