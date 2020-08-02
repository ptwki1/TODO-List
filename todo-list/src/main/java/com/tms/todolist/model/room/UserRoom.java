package com.tms.todolist.model.room;

import com.tms.todolist.model.User;
import com.tms.todolist.model.room.Room;
import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;

@Data
@Builder
@Entity
@AllArgsConstructor
@NoArgsConstructor
public class UserRoom {

    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long id;

    @ManyToOne
    private Room room;

    @ManyToOne
    private User user;


    private RequestStatus requestStatus;

    public enum RequestStatus {
        PENDING, ACCEPTED, DECLINED
    }
}
