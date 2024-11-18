package com.lobotomia.lobotomia.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Entity
public class Orders {
    @Id
    @GeneratedValue
    UUID id;
    @NotEmpty
    String title;
    @Min(500)
    Integer price;

    @ManyToMany
    @JoinTable(name = "user_order",joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    List<Users> users;

    public Orders() {}

    public Orders(UUID id, String title, Integer price, List<Users> users) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.users = users;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotEmpty String getTitle() {
        return title;
    }

    public void setTitle(@NotEmpty String title) {
        this.title = title;
    }

    public @Min(500) Integer getPrice() {
        return price;
    }

    public void setPrice(@Min(500) Integer price) {
        this.price = price;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }
}
