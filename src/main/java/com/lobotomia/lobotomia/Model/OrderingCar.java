package com.lobotomia.lobotomia.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotNull;

import java.util.Date;
import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "ordering_car")
public class OrderingCar {
    @Id
    @GeneratedValue
    UUID id;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne(fetch = FetchType.LAZY)
    Cars cars;

    @NotNull
    Date date;

    @ManyToMany
    @JoinTable(name = "user_order",joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "user_id"))
    List<Users> users;

    @ManyToMany
    @JoinTable(name = "additional_serices_by_car", joinColumns = @JoinColumn(name = "order_id"), inverseJoinColumns = @JoinColumn(name = "service_id"))
    List<AdditionalServices> services;

    public OrderingCar() {
    }

    public OrderingCar(UUID id, Cars cars, Date date, List<Users> users, List<AdditionalServices> services) {
        this.id = id;
        this.cars = cars;
        this.date = date;
        this.users = users;
        this.services = services;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public Cars getCars() {
        return cars;
    }

    public void setCars(Cars cars) {
        this.cars = cars;
    }

    public @NotNull Date getDate() {
        return date;
    }

    public void setDate(@NotNull Date date) {
        this.date = date;
    }

    public List<Users> getUsers() {
        return users;
    }

    public void setUsers(List<Users> users) {
        this.users = users;
    }

    public List<AdditionalServices> getServices() {
        return services;
    }

    public void setServices(List<AdditionalServices> services) {
        this.services = services;
    }
}
