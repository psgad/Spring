package com.lobotomia.lobotomia.controllers.API;

import com.lobotomia.lobotomia.Model.Profile;
import com.lobotomia.lobotomia.Model.Roles;
import com.lobotomia.lobotomia.Model.Student;
import com.lobotomia.lobotomia.Service.BaseService;
import com.lobotomia.lobotomia.Service.ProfileService;
import com.lobotomia.lobotomia.Service.RolesService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/api/students")
public class APIRoles extends BaseAPI<Student, UUID> {
    @Autowired
    protected APIRoles(BaseService<Student, UUID> baseService) {
        super(baseService);
    }
}
