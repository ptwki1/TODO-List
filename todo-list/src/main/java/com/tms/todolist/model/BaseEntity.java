package com.tms.todolist.model;

import lombok.Data;

import javax.persistence.*;
import java.util.Date;
@Data
@MappedSuperclass
@Table(indexes ={
        @Index(columnList = "created"),
        @Index(columnList = "update"),
        @Index(columnList = "active")})
public class BaseEntity {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;
    @Column
    private Date created;
    @Column
    private Date update;
    @Column
    private Status active;
}
