package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.AdditionalServices;
import com.lobotomia.lobotomia.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/servicesList")
public class AdditionalServicesController extends BaseController<AdditionalServices, UUID> {
    @Autowired
    public AdditionalServicesController(BaseService<AdditionalServices, UUID> baseService) {
        super(baseService, AdditionalServices.class, "servicesList", "service");
    }
}
