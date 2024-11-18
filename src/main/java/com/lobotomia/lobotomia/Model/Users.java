package com.lobotomia.lobotomia.Model;

import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import org.hibernate.query.Order;
import org.hibernate.validator.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;
import org.springframework.beans.factory.annotation.Value;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    UUID id;
    @NotNull
    String firstName;
    @NotNull
    String surName;
    @Nullable
    String lastName;
    @ManyToOne
    @JoinColumn(name = "role_id")
    Roles roles;
    @OneToOne
    @JoinColumn(name = "profile_id")
    Profile profile;
    @ManyToMany(mappedBy = "users")
    List<Orders> orders;

    public Users(){}

    public Users(UUID id, String firstName, String surName, @Nullable String lastName, Roles roles, Profile profile, List<Orders> orders) {
        this.id = id;
        this.firstName = firstName;
        this.surName = surName;
        this.lastName = lastName;
        this.roles = roles;
        this.profile = profile;
        this.orders = orders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotNull String getFirstName() {
        return firstName;
    }

    public void setFirstName(@NotNull String firstName) {
        this.firstName = firstName;
    }

    public @NotNull String getSurName() {
        return surName;
    }

    public void setSurName(@NotNull String surName) {
        this.surName = surName;
    }

    @Nullable
    public String getLastName() {
        return lastName;
    }

    public void setLastName(@Nullable String lastName) {
        this.lastName = lastName;
    }

    public Roles getRoles() {
        return roles;
    }

    public void setRoles(Roles roles) {
        this.roles = roles;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<Orders> getOrders() {
        return orders;
    }

    public void setOrders(List<Orders> orders) {
        this.orders = orders;
    }
}
