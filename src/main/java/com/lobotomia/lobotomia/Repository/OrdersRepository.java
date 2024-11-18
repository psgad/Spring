package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.Orders;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface OrdersRepository extends JpaRepository<Orders, UUID> {
}
