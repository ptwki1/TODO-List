package com.tms.todolist.model;

import javax.persistence.*;
import java.util.List;

@Entity
@lombok.Data
public class Role extends BaseEntity {
    private String name;// (роль пользователя)

    @ManyToMany(mappedBy = "roles", fetch = FetchType.LAZY)
    private List<User> users;

}
