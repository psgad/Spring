package com.lobotomia.lobotomia.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import jakarta.annotation.Nullable;
import jakarta.persistence.*;
import jakarta.validation.constraints.*;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;

import java.util.Date;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.format.annotation.DateTimeFormat;

@Entity
@Table(name = "users")
public class Users {
    @Id
    @GeneratedValue
    UUID id;

    @NotEmpty
    String middleName;

    @NotEmpty
    String firstname;

    @Nullable
    String lastname;

    @NotNull
    @DateTimeFormat(pattern = "yyyy-MM-dd")
    Date Date;

    @Nullable
    String proneNumber;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne(fetch = FetchType.LAZY)
    Profile profile;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToMany(cascade = CascadeType.ALL)
    @JoinTable(name = "user_order", joinColumns = @JoinColumn(name = "user_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    List<OrderingCar> orders;

    public Users() {
    }

    public Users(UUID id, String middleName, String firstname, @Nullable String lastname, java.util.Date date, @Nullable String proneNumber, Profile profile, List<OrderingCar> orders) {
        this.id = id;
        this.middleName = middleName;
        this.firstname = firstname;
        this.lastname = lastname;
        Date = date;
        this.proneNumber = proneNumber;
        this.profile = profile;
        this.orders = orders;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotEmpty String getMiddleName() {
        return middleName;
    }

    public void setMiddleName(@NotEmpty String middleName) {
        this.middleName = middleName;
    }

    public @NotEmpty String getFirstname() {
        return firstname;
    }

    public void setFirstname(@NotEmpty String firstname) {
        this.firstname = firstname;
    }

    @Nullable
    public String getLastname() {
        return lastname;
    }

    public void setLastname(@Nullable String lastname) {
        this.lastname = lastname;
    }

    public java.util.@NotNull Date getDate() {
        return Date;
    }

    public void setDate(java.util.@NotNull Date date) {
        Date = date;
    }

    @Nullable
    public String getProneNumber() {
        return proneNumber;
    }

    public void setProneNumber(@Nullable String proneNumber) {
        this.proneNumber = proneNumber;
    }

    public Profile getProfile() {
        return profile;
    }

    public void setProfile(Profile profile) {
        this.profile = profile;
    }

    public List<OrderingCar> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderingCar> orders) {
        this.orders = orders;
    }
}
