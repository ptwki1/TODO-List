package com.tms.todolist.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
@Data
@MappedSuperclass
@AllArgsConstructor
@Table(indexes ={
        @Index(columnList = "created"),
        @Index(columnList = "update"),
        @Index(columnList = "active")})
@NoArgsConstructor
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column(updatable = false)
    private Date created;
    @Column
    private Date updated;
    @Column
    private boolean active; //сделать 2 разных поля для User and Task
}
