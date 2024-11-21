package com.lobotomia.lobotomia.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotEmpty;
import jakarta.validation.constraints.NotNull;
import org.hibernate.annotations.IdGeneratorType;

import java.util.List;
import java.util.UUID;

@Entity
@Table(name = "providers")
public class Providers {
    @Id
    @GeneratedValue
    UUID id;

    @NotEmpty
    String title;

    @NotEmpty
    String description;

    @NotNull
    Long INN;

    @Min(1)
    @Max(10)
    int rate;


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToMany(fetch = FetchType.LAZY)
    List<Car_info> carInfo;

    public Providers(){}

    public Providers(UUID id, String title, String description, Long INN, int rate, List<Car_info> carInfo) {
        this.id = id;
        this.title = title;
        this.description = description;
        this.INN = INN;
        this.rate = rate;
        this.carInfo = carInfo;
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

    public @NotNull Long getINN() {
        return INN;
    }

    public void setINN(@NotNull Long INN) {
        this.INN = INN;
    }

    @Min(1)
    @Max(10)
    public int getRate() {
        return rate;
    }

    public void setRate(@Min(1) @Max(10) int rate) {
        this.rate = rate;
    }

    public List<Car_info> getCarInfo() {
        return carInfo;
    }

    public void setCarInfo(List<Car_info> carInfo) {
        this.carInfo = carInfo;
    }
}
