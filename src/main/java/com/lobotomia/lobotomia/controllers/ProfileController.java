package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Profile;
import com.lobotomia.lobotomia.Model.RoleEnum;
import com.lobotomia.lobotomia.Service.BaseService;
import com.lobotomia.lobotomia.Service.ProfileService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.Collections;
import java.util.UUID;

@Controller
@RequestMapping("/profiles")
public class ProfileController extends BaseController<Profile, UUID> {
    @Autowired
    public ProfileService profileService;
    @Autowired
    public PasswordEncoder passwordEncoder;

    public ProfileController(BaseService<Profile, UUID> baseService) {
        super(baseService, Profile.class, "profiles", "profile");
    }

    @Override
    @PostMapping("/update")
    public String edit(@Valid @ModelAttribute("profile") Profile profile, BindingResult result) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profile.setActive(true);
        profile.setRoles(switch (profile.getUsername()) {
            case "admin" -> Collections.singleton(RoleEnum.ADMIN);
            case "manager" -> Collections.singleton(RoleEnum.MANAGER);
            default -> Collections.singleton(RoleEnum.USER);
        });
        profileService.edit(profile.getId(), profile);
        return "redirect:/profiles/all";

    }

    @Override
    @PostMapping("/add")
    public String create(@Valid @ModelAttribute("profile") Profile profile, BindingResult result, Model model) {
        profile.setPassword(passwordEncoder.encode(profile.getPassword()));
        profile.setActive(true);
        profile.setRoles(switch (profile.getUsername()) {
            case "admin" -> Collections.singleton(RoleEnum.ADMIN);
            case "manager" -> Collections.singleton(RoleEnum.MANAGER);
            default -> Collections.singleton(RoleEnum.USER);
        });
        profileService.add(profile);
        return "redirect:/profiles/all";

    }
}