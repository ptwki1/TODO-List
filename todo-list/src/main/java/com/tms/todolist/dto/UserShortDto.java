package com.tms.todolist.dto;

import lombok.Builder;
import lombok.Value;

@Value
@Builder
public class UserShortDto {
  Long id;
  String name;
  String login;
  boolean active;
}
