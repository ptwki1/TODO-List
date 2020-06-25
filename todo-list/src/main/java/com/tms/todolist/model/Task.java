package com.tms.todolist.model;

import javax.persistence.*;
import java.util.List;

@lombok.Data
@Entity
public class Task extends BaseEntity {


    @Column
    private String description;//(описание задания)

    @Column
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    @ManyToMany(mappedBy = "tasks", fetch = FetchType.LAZY)
    private List<User> users;

//    TaskGroup group;// (к какой группе будет относиться задача)

}
