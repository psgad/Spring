package com.lobotomia.lobotomia.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import org.apache.catalina.User;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "roles")
public class Roles {
    @Id
    @GeneratedValue
    UUID id;
    @NotNull
    @Size(min = 2, max = 10)
    String title;

    @OneToMany(mappedBy = "roles", cascade = CascadeType.ALL)
    List<Users> users;

    public Roles() {}

    public Roles(UUID id, String title, List<Users> users) {
        this.id = id;
        this.title = title;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull @Size(min = 2, max = 10) String getTitle() {
        return title;
    }

    public void setTitle(@NotNull @Size(min = 2, max = 10) String title) {
        this.title = title;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
