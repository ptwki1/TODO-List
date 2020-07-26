package com.tms.todolist.model;

import com.tms.todolist.model.room.TaskRoom;
import com.tms.todolist.model.room.UserRoom;
import lombok.Builder;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.Date;
import java.util.List;

@Entity
@Table(name = "users")
@lombok.Data
@NoArgsConstructor
public class User extends BaseEntity {


    @Builder
    public User(Long id, Date created, Date updated,
                boolean active, String login,
                String password, String name, String avatar,
                List<Task> tasks, List<Role> roles) {
        super(id, created, updated, active);
        this.login = login;
        this.password = password;
        this.name = name;
        this.avatar = avatar;
        this.tasks = tasks;
        this.roles = roles;
    }

    @Column
    private String name;
    @Column
    private String avatar;
    @Column
    private String login;//( логин для входа в приложение и его распознавания - email)
    @Column
    private String password;//(пароль для входа и проверки пользователя)

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_tasks",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "task_id", referencedColumnName = "id")})
    private List<Task> tasks; //список всех задач

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private List<Role> roles;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<UserRoom> userRooms;

    @OneToMany(fetch = FetchType.LAZY, mappedBy = "user")
    private List<TaskRoom> taskRoomList;

}
