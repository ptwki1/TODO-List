package com.tms.todolist.model;

import org.hibernate.annotations.Fetch;
import org.hibernate.annotations.FetchMode;
import org.hibernate.annotations.IndexColumn;
import org.hibernate.annotations.Type;
import org.springframework.data.util.Pair;

import javax.persistence.*;
import java.util.List;
@Entity
@Table(name = "users")
@lombok.Data
public class User extends Person {

    @Column
    private String username;//( логин для входа в приложение и его распознавания - email)


    @Column
    private String password;//(пароль для входа и проверки пользователя)

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_tasks",
    joinColumns ={@JoinColumn(name = "user_id",referencedColumnName = "id")},
    inverseJoinColumns = {@JoinColumn(name = "task_id",referencedColumnName = "id")})
    @OrderColumn
    private List<Task> tasks; //список всех задач

    @ManyToMany(fetch = FetchType.LAZY)
    @JoinTable(name = "user_roles",
            joinColumns = {@JoinColumn(name = "user_id", referencedColumnName = "id")},
            inverseJoinColumns = {@JoinColumn(name = "role_id", referencedColumnName = "id")})
    @OrderColumn
    private  List<Role> roles;


//    @Type(type = "json")
//    @Column(columnDefinition = "json")
//    private List<Friend> friends;// список друзей

}
