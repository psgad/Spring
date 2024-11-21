package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.OrderingCar;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrderingCarRepository extends JpaRepository<OrderingCar, UUID> {

}
