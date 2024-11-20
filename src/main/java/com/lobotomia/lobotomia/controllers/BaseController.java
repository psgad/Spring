package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Student;
import com.lobotomia.lobotomia.Service.BaseService;
import com.lobotomia.lobotomia.Service.StudentService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public abstract class BaseController<T, ID> {
    @Autowired
    public BaseService<T, ID> baseService;
    Class<T> cls;
    String title_list, title_model;

    public BaseController(BaseService<T, ID> baseService, Class<T> cls, String title_list, String title_model) {
        this.baseService = baseService;
        this.cls = cls;
        this.title_list = title_list;
        this.title_model = title_model;
    }

    @GetMapping("/all")
    public String getAll(Model model,
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page,
                              @RequestParam(name = "FIO", required = false) String FIO,
                              @RequestParam(name = "group", required = false) String group,
                              @RequestParam(name = "course", required = false) String course) throws InstantiationException, IllegalAccessException {
        List<T> list = baseService.findAll();
        System.out.println("количество " + title_model + ": " + list.size());
        model.addAttribute(title_list, list);
        model.addAttribute(title_model, cls.newInstance());

        return "students";

    }

    @PostMapping("/add")
    public String create(@Valid T entity, BindingResult result, Model model) throws InstantiationException, IllegalAccessException {
        if (result.hasErrors()) {
            List<T> list = baseService.findAll();
            System.out.println("количество " + title_model + ": " + list.size());
            model.addAttribute(title_list, list);
            model.addAttribute(title_model, cls.newInstance());
        }
        baseService.add(entity);
        return "redirect:/students/all";


    }

    @PostMapping("/update")
    public String edit(@Valid T entity, BindingResult result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        baseService.edit((ID) entity.getClass().getMethod("getId").invoke(entity), entity);
        return "redirect:/students/all";

    }

    @PostMapping("/delete")
    public String delete(@RequestBody ArrayList<ID> ids) {
        for (ID id : ids) {
            baseService.delete(id);
        }
        return "redirect:/students/all";

    }

}
