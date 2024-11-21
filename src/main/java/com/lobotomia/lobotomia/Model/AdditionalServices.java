package com.lobotomia.lobotomia.Model;

import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "additional_services")
public class AdditionalServices {
    @Id
    @GeneratedValue
    UUID id;

    @NotEmpty
    String title;

    @NotEmpty
    String description;

    int price;


    @ManyToMany
    @JoinTable(name = "additional_serices_by_car", joinColumns = @JoinColumn(name = "service_id"), inverseJoinColumns = @JoinColumn(name = "order_id"))
    List<OrderingCar> orders;

    public AdditionalServices() {
    }

    public AdditionalServices(UUID id, String title, String description, int price, List<OrderingCar> orders) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.price = price;
        this.orders = orders;
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

    public @NotEmpty String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty String description) {
        this.description = description;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public List<OrderingCar> getOrders() {
        return orders;
    }

    public void setOrders(List<OrderingCar> orders) {
        this.orders = orders;
    }
}
