package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Model.Users;
import com.lobotomia.lobotomia.Service.BaseService;
import com.lobotomia.lobotomia.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.util.UUID;

@Controller
@RequestMapping("/users")
public class UsersController extends BaseController<Users, UUID> {
    public UsersController(BaseService<Users, UUID> baseService) {
        super(baseService, Users.class, "users", "user");
    }
    @Autowired
    ProfileService profileService;

    @Override
    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException {
        Pagination<Users> list = baseService.findAll(page);
        System.out.println("количество " + title_model + ": " + list.getCurrentItems().size());
        model.addAttribute(title_list, list);
        model.addAttribute(title_model, cls.newInstance());
        model.addAttribute("profiles", profileService.findAll());

        return title_list;

    }
}
