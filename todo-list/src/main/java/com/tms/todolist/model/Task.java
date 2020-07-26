package com.tms.todolist.model;

import com.tms.todolist.model.room.TaskRoom;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
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
        this.users = users;
    }

    @Column
    private String description;//(описание задания)

    @Column
    @Enumerated(value = EnumType.STRING)
    private TaskStatus status;


    @ManyToMany(mappedBy = "tasks")
    private List<User> users;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "task")
    private List<TaskRoom> taskRoomList;

//    public boolean isActive() {
////        return endDate == null || LocalDateTime.now().compareTo(endDate) <= 0 && (status check);
//        return false;
//    }


}
