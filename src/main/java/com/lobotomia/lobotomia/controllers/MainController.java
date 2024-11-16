package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Model.Users;
import com.lobotomia.lobotomia.Service.UserService;
import jakarta.validation.Valid;
import org.apache.catalina.User;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

@Controller
@RequestMapping("/users")
public class MainController {
    @Autowired
    public UserService userService;


    @GetMapping("/all")
    public String getAllUsers(Model model,
                       @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                       @RequestParam(name = "firstname", required = false) String firstName,
                       @RequestParam(name = "surname", required = false) String surname,
                       @RequestParam(name = "lastname", required = false) String lastname,
                       @RequestParam(name = "role", required = false) String role) {
        List<Users> users = userService.findAllUsers();
        System.out.println("количество пользователей: " + users.size());
        model.addAttribute("pagination_users", users);
        model.addAttribute("user", new Users());

        return "users";

    }

    @PostMapping("/add")
    public String addUser(@Valid @ModelAttribute("users") Users user, BindingResult result, Model model) {
        if (result.hasErrors()) {
            List<Users> users = userService.findAllUsers();
            model.addAttribute("pagination_users", users);
            model.addAttribute("users", userService.findAllUsers());
            return "users";
        }
        userService.addUser(user);
        return "redirect:/users/all";


    }

    @PostMapping("/update")
    public String updateUser(@Valid @ModelAttribute("users") Users user, BindingResult result) {
        userService.editUser(user);
        return "redirect:/users/all";

    }

    @PostMapping("/delete")
    public String deleteUser(@RequestBody ArrayList<Long> ids) {
        for(Long id : ids) {
            userService.deleteUser(id);
        }
        return "redirect:/users/all";

    }
    @GetMapping("/all/{id}")
    public String getIdStudent(@PathVariable("id") Long id, Model model) {
        model.addAttribute("users", userService.findUserById(id));
        model.addAttribute("user", new Users());
        return "users";
    }


}

