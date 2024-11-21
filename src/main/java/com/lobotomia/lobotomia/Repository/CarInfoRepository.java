package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.Car_info;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface CarInfoRepository extends JpaRepository<Car_info, UUID> {
    
}
