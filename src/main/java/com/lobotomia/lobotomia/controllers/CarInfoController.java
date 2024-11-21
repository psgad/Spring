package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Car_info;
import com.lobotomia.lobotomia.Model.Cars;
import com.lobotomia.lobotomia.Service.BaseService;
import com.lobotomia.lobotomia.Service.CarsService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.Date;
import java.util.UUID;

@Controller
@RequestMapping("/carsInfo")
public class CarInfoController extends BaseController<Car_info, UUID> {

    @Autowired
    CarsService carsService;

    @Autowired
    public CarInfoController(BaseService<Car_info, UUID> baseService) {
        super(baseService, Car_info.class, "carsInfo", "carInfo");
    }

    @PostMapping("/add/{id}")
    public String create(@PathVariable("id") UUID id, @Valid @ModelAttribute("car_info") Car_info entity, BindingResult result, Model model) throws InstantiationException, IllegalAccessException {
        entity.setDateCreation(new Date());
        Cars car = carsService.findById(id);
        entity.setCar(car);
        car.setCar_info(entity);
        carsService.edit(id, car);
        if (result.hasErrors()) {
            return "redirect:/" + "carsList/" + id;
        }
        baseService.add(entity);
        return "redirect:/" + "carsList/" + id;
    }

    @Override
    @PostMapping("/update")
    public String edit(@Valid @ModelAttribute("car_info") Car_info entity, BindingResult result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        entity.setDateCreation(baseService.findById(entity.getId()).getDateCreation() == null ? new Date() : baseService.findById(entity.getId()).getDateCreation());
        entity.setCar(baseService.findById(entity.getId()).getCar());
        baseService.edit(entity.getId(), entity);
        return "redirect:/" + title_list + "/all";

    }

    @PostMapping("/delete/{id}")
    public String delete(@PathVariable("id") UUID id) {
        baseService.delete(id);
        return "redirect:/" + title_list + "/all";

    }

}
