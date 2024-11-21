package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.Cars;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarsRepository extends JpaRepository<Cars, UUID> {

}
