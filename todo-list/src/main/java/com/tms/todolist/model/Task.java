package com.tms.todolist.model;

import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.time.LocalDateTime;
import java.util.Date;
import java.util.List;

@Data
@Entity
@NoArgsConstructor
public class Task extends BaseEntity {

    @Builder
    public Task(Long id, Date created, Date updated, boolean active, String description, TaskStatus status, List<User> users) {
        super(id, created, updated, active);
        this.description = description;
        this.status = status;
//        this.group=group;
        this.users = users;
    }

    @Column
    private String description;//(описание задания)

    //    @Column
//    @Enumerated(value = EnumType.STRING)
//    private TaskGroup group;
//
    @Column
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;

    private LocalDateTime endDate;

    @ManyToMany(mappedBy = "tasks")
    private List<User> users;

    public boolean isActive() {
//        return endDate == null || LocalDateTime.now().compareTo(endDate) <= 0 && (status check);
        return false;
    }

//    TaskGroup group;// (к какой группе будет относиться задача)

}
