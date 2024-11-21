package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Providers;
import com.lobotomia.lobotomia.Service.BaseService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.UUID;

@Controller
@RequestMapping("/providersList")
public class ProviderController extends BaseController<Providers, UUID> {
    @Autowired
    public ProviderController(BaseService<Providers, UUID> baseService) {
        super(baseService, Providers.class, "providersList", "provider");
    }
}
