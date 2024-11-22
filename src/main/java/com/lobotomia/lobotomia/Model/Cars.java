package com.lobotomia.lobotomia.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "cars")
public class Cars {
    @Id
    @GeneratedValue
    UUID id;

    @NotEmpty
    String title;

    @Min(250)
    int price;

    int count;

    boolean isDeleted;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne(mappedBy = "cars", cascade = CascadeType.ALL)
    Car_info car_info;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(mappedBy = "cars", cascade = CascadeType.ALL)
    List<OrderingCar> orderingCar;



    public Cars(){}

    public Cars(UUID id, String title, int price, int count, boolean isDeleted, Car_info car_info, List<OrderingCar> orderingCar) {
        this.id = id;
        this.title = title;
        this.price = price;
        this.count = count;
        this.isDeleted = isDeleted;
        this.car_info = car_info;
        this.orderingCar = orderingCar;
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

    @Min(250)
    public int getPrice() {
        return price;
    }

    public void setPrice(@Min(250) int price) {
        this.price = price;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public Car_info getCar_info() {
        return car_info;
    }

    public void setCar_info(Car_info car_info) {
        this.car_info = car_info;
    }

    public List<OrderingCar> getOrderingCar() {
        return orderingCar;
    }

    public void setOrderingCar(List<OrderingCar> orderingCar) {
        this.orderingCar = orderingCar;
    }

    public boolean isDeleted() {
        return isDeleted;
    }

    public void setDeleted(boolean deleted) {
        isDeleted = deleted;
    }
}
