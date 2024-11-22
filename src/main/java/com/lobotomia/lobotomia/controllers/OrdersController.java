package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.OrderingCar;
import com.lobotomia.lobotomia.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/orders")
public class OrdersController extends BaseController<OrderingCar, UUID> {
    @Autowired
    public OrdersController(BaseService<OrderingCar, UUID> baseService) {
        super(baseService, OrderingCar.class, "orders", "order");
    }
}
