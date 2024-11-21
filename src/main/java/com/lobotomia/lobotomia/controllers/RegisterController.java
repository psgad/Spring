package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Profile;
import com.lobotomia.lobotomia.Model.RoleEnum;
import com.lobotomia.lobotomia.Model.Users;
import com.lobotomia.lobotomia.Repository.ProfileRepository;
import com.lobotomia.lobotomia.Service.UsersService;
import jakarta.servlet.http.Cookie;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.CookieValue;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;

import java.util.Collection;
import java.util.Collections;
import java.util.Map;
import java.util.UUID;

@Controller
public class RegisterController {
    @Autowired
    private ProfileRepository profileRepository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    UsersService usersService;

    @GetMapping("/reg")
    public String registrationView(Model model) {
        model.addAttribute("profile", new Profile());

        return "reg";
    }

    @PostMapping("/reg")
    public String registrationUser(Profile user, Model model, @CookieValue(value = "user_id", defaultValue = "NoToken") String userToken) {
        if(userToken.equals("NoToken")){
            model.addAttribute("message", "Ошибка сохранения нового пользователя");
            return "reg";
        }
        if (profileRepository.existsByUsername(user.getUsername())) {
            model.addAttribute("message", "Пользователь с таким именем уже есть");
            return "reg";
        }
        if (user.getUsername().length() < 3) {
            model.addAttribute("message", "Имя пользователя должно быть не менее 3 символов");
            return "reg";
        }
        if (user.getPassword().length() < 8) {
            model.addAttribute("message", "Пароль должен быть не менее 8 символов");
            return "reg";
        }
        if (!validation(user.getPassword())) {
            model.addAttribute("message", "Слабый пароль");
            return "reg";
        }
        user.setActive(true);
        user.setRoles(Collections.singleton(RoleEnum.USER));
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        profileRepository.save(user);
        UUID id = UUID.fromString(userToken);
        Users users = usersService.findById(id);
        users.setProfile(user);
        usersService.edit(id, users);

        return "redirect:/login";
    }

    private boolean validation(String password) {
        int result = 0;
        if (password.matches(".*[A-Z].*") || password.matches(".*[А-Я].*"))
            result++;
        if (password.matches(".*[a-z].*") || password.matches(".*[а-я].*"))
            result++;
        if (password.matches(".*\\d.*"))
            result++;
        if (password.matches(".*[!@#$%^&*()\\-_=+{};:,<.>].*"))
            result++;
        return result >= 2;
    }

    @GetMapping("/reg/user")
    private String regUser(Model model, HttpServletRequest request) {
        Cookie[] cookies = request.getCookies();
        if (cookies != null) {
            for (Cookie cookie : cookies) {
                if ("user_id".equals(cookie.getName())) {
                    cookie.setMaxAge(0);
                    cookie.setPath("/");
                    cookie.setHttpOnly(true);
                    break;
                }
            }
        }
        model.addAttribute("user", new Users());
        return "RegistrationUser";
    }

    @PostMapping("/reg/user")
    private String regUserAccount(@Valid @ModelAttribute("users") Users user, Model model, HttpServletResponse response) {
        usersService.add(user);
        Cookie cookie = new Cookie("user_id", user.getId().toString());
        cookie.setHttpOnly(true);
        cookie.setSecure(true);
        cookie.setMaxAge(7 * 24 * 60 * 60);
        cookie.setPath("/");
        response.addCookie(cookie);
        return "redirect:/reg";
    }


}