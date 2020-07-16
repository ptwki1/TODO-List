package com.tms.todolist.dto;

import com.tms.todolist.model.TaskStatus;
import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class TaskShortDto {
    Long id;
    String description;
    TaskStatus status;
    boolean active;
}
