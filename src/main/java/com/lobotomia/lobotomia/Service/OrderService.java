package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.UUID;

@Service
public class OrderService extends BaseService<OrderingCar, UUID> {
    @Autowired
    public OrderService(JpaRepository<OrderingCar, UUID> repository) {
        super(repository);
    }
}


