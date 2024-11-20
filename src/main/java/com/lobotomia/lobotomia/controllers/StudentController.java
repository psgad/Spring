package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Student;
import com.lobotomia.lobotomia.Service.BaseService;
import com.lobotomia.lobotomia.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
@RequestMapping("/students")
public class StudentController extends BaseController<Student, UUID> {

    @Autowired
    public StudentController(BaseService<Student, UUID> baseService) {
        super(baseService, Student.class, "pagination_students", "student");
    }
}