package com.lobotomia.lobotomia.Model;

import com.fasterxml.jackson.annotation.JsonIdentityInfo;
import com.fasterxml.jackson.annotation.ObjectIdGenerators;
import jakarta.persistence.*;
import jakarta.validation.constraints.NotEmpty;

import java.util.Date;
import java.util.UUID;

@Entity
@Table(name = "car_info")
public class Car_info {
    @Id
    @GeneratedValue
    UUID id;

    @NotEmpty
    String description;

    Date dateCreation;

    @NotEmpty
    String marka;

    @NotEmpty
    String model;

    @NotEmpty
    String color;

    int probeg;

    @NotEmpty
    String typeTransmission;

    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @OneToOne(fetch = FetchType.LAZY)
    Cars cars;


    @JsonIdentityInfo(generator = ObjectIdGenerators.PropertyGenerator.class, property = "id")
    @ManyToOne
    @JoinColumn(name = "provider_id")
    Providers provider;

    public Car_info(){}

    public Car_info(UUID id, String description, Date dateCreation, String marka, String model, String color, int probeg, String typeTransmission, Cars car, Providers provider) {
        this.id = id;
        this.description = description;
        this.dateCreation = dateCreation;
        this.marka = marka;
        this.model = model;
        this.color = color;
        this.probeg = probeg;
        this.typeTransmission = typeTransmission;
        this.cars = car;
        this.provider = provider;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public @NotEmpty String getDescription() {
        return description;
    }

    public void setDescription(@NotEmpty String description) {
        this.description = description;
    }

    public Date getDateCreation() {
        return dateCreation;
    }

    public void setDateCreation(Date dateCreation) {
        this.dateCreation = dateCreation;
    }

    public @NotEmpty String getMarka() {
        return marka;
    }

    public void setMarka(@NotEmpty String marka) {
        this.marka = marka;
    }

    public @NotEmpty String getModel() {
        return model;
    }

    public void setModel(@NotEmpty String model) {
        this.model = model;
    }

    public @NotEmpty String getColor() {
        return color;
    }

    public void setColor(@NotEmpty String color) {
        this.color = color;
    }

    public int getProbeg() {
        return probeg;
    }

    public void setProbeg(int probeg) {
        this.probeg = probeg;
    }

    public @NotEmpty String getTypeTransmission() {
        return typeTransmission;
    }

    public void setTypeTransmission(@NotEmpty String typeTransmission) {
        this.typeTransmission = typeTransmission;
    }

    public Cars getCar() {
        return cars;
    }

    public void setCar(Cars car) {
        this.cars = car;
    }

    public Providers getProvider() {
        return provider;
    }

    public void setProvider(Providers provider) {
        this.provider = provider;
    }
}
