package com.tms.todolist.model;

import lombok.Data;

import javax.persistence.Column;
import javax.persistence.Entity;

@Data
@Entity
public class Person extends BaseEntity {
    @Column
    private String name;
    @Column
    private String avatar;
}
