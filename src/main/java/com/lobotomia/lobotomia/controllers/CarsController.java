package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.*;
import com.lobotomia.lobotomia.Service.*;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.UUID;


@Controller
@RequestMapping("/carsList")
public class CarsController extends BaseController<Cars, UUID> {
    @Autowired
    public CarsController(BaseService<Cars, UUID> baseService) {
        super(baseService, Cars.class, "carsList", "car");
    }

    @Autowired
    CarsService carsService;
    @Autowired
    ProviderService providerService;
    @Autowired
    AdditionalServicesService additionalServicesService;
    @Autowired
    OrderService orderService;
    @Autowired
    UsersService usersService;
    @Autowired
    ProfileService profileService;

    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) {
        Pagination<Cars> list = baseService.findAll(page);
        System.out.println("количество " + title_model + ": " + list.getCurrentItems().size());
        model.addAttribute(title_list, list);
        model.addAttribute(title_model, new Cars());
        if (getCurrentUser().getUsername().equals("manager"))
            model.addAttribute("manager", true);
        else model.addAttribute("manager", false);
        return title_list;

    }

    @GetMapping("/{id}")
    public String getAll(@PathVariable("id") UUID id, Model model) {
        Car_info car = baseService.findById(id).getCar_info();
        model.addAttribute("carInfo", car);
        if (car == null)
            model.addAttribute("car_id", id);
        model.addAttribute("newCarInfo", new Car_info());
        if (getCurrentUser().getUsername().equals("manager"))
            model.addAttribute("manager", true);
        else {
            model.addAttribute("manager", false);
            model.addAttribute("services", additionalServicesService.findAll());
        }
        model.addAttribute("providers", providerService.findAll());
        model.addAttribute("car_id", id);
        return "carInfo";

    }

    @PostMapping("/{id}/add_order")
    public String createOrder(@PathVariable("id") UUID id, @Nullable @RequestBody List<UUID> ids) {
        OrderingCar order = new OrderingCar();
        order.setCars(baseService.findById(id));
        order.setDate(new Date());
        Users user = usersService.findById(getCurrentUserId());
        List<Users> users = order.getUsers() == null ? new ArrayList<>() : order.getUsers();
        users.add(user);
        order.setUsers(users);
        if (ids != null) {
            List<AdditionalServices> services = order.getServices() == null ? new ArrayList<>() : order.getServices();
            for (UUID additional_id : ids) {
                AdditionalServices service = additionalServicesService.findById(additional_id);
                if (service != null)
                    services.add(service);
            }
            order.setServices(services);
        }
        orderService.add(order);
        baseService.delete(id);
        return "redirect:/carsList/all";

    }

    public  UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            return (UserDetails) principal;
        }
        return null;
    }

    public  UUID getCurrentUserId() {
        String currentUsername = getCurrentUser().getUsername();
        for (Profile profile : profileService.findAll())
            if (profile.getUsername().equals(currentUsername))
                return profile.getUsers().getId();
        return null;
    }

}
