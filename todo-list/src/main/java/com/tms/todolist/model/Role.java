package com.tms.todolist.model;

import javax.persistence.*;

@Entity
@lombok.Data
public class Role {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;//(id роли в бд)

    @Column
    private String name;// (роль пользователя)

}
