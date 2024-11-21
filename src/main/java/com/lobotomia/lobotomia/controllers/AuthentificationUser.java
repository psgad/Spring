package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Profile;
import com.lobotomia.lobotomia.Service.ProfileService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.security.core.userdetails.UserDetails;

import java.util.UUID;

public class AuthentificationUser {
    @Autowired
    static ProfileService profileService;

    public static UserDetails getCurrentUser() {
        Authentication authentication = SecurityContextHolder.getContext().getAuthentication();
        if (authentication != null && authentication.isAuthenticated()) {
            Object principal = authentication.getPrincipal();
            return (UserDetails) principal;
        }
        return null;
    }

    public static UUID getCurrentUserId() {
        String currentUsername = getCurrentUser().getUsername();
        for (Profile profile : profileService.findAll())
            if (profile.getUsername().equals(currentUsername))
                return profile.getUsers().getId();
        return null;
    }
}
