package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Car_info;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class CarInfoService extends BaseService<Car_info, UUID> {
    @Autowired
    public CarInfoService(JpaRepository<Car_info, UUID> repository) {
        super(repository);
    }
}
