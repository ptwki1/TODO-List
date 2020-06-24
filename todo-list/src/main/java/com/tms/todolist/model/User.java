package com.tms.todolist.model;

import org.hibernate.annotations.Type;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.List;
@Entity
@lombok.Data
public class User extends Person {

    @Column
    private String username;//( логин для входа в приложение и его распознавания - email)


    @Column
    private String password;//(пароль для входа и проверки пользователя)

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_tasks",
    joinColumns ={@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "task_id",referencedColumnName = "id")})
    private List<Task> tasks; //список всех задач

    @ManyToMany(fetch = FetchType.EAGER)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    private  List<Role> roles;


//    @Type(type = "json")
//    @Column(columnDefinition = "json")
//    private List<Friend> friends;// список друзей

}
