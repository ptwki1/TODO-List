package com.tms.todolist.model.room;

import com.tms.todolist.model.BaseEntity;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;


@Entity
@Data
@NoArgsConstructor
public class Room extends BaseEntity {
    @Builder
    public Room(Long id, Date created, Date updated,
                boolean active,
                String name,
                List<UserRoom> userRooms,
                List<TaskRoom> taskRoomList) {
        super(id, created, updated, active);
        this.name = name;
        this.userRooms = userRooms;
        this.taskRoomList = taskRoomList;
    }

    @Column
    private String name;

//    @Column
//    @Enumerated(value = EnumType.STRING)
//    private TaskGroup group;

    @OneToMany(fetch = FetchType.LAZY, cascade = CascadeType.ALL, orphanRemoval = true, mappedBy = "room")
    private List<UserRoom> userRooms;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "room")
    private List<TaskRoom> taskRoomList;

}
