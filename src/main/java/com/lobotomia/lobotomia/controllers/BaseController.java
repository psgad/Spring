package com.lobotomia.lobotomia.controllers;

import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Service.BaseService;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;

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
                              @RequestParam(name = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException {
        Pagination<T> list = baseService.findAll(page);
        System.out.println("количество " + title_model + ": " + list.getCurrentItems().size());
        model.addAttribute(title_list, list);
        model.addAttribute(title_model, cls.newInstance());

        return title_list;

    }

    @PostMapping("/add")
    public String create(@Valid T entity, BindingResult result, Model model) throws InstantiationException, IllegalAccessException {
        if (result.hasErrors()) {
            return "redirect:/"+ title_list + "/all";
        }
        baseService.add(entity);
        return "redirect:/"+ title_list + "/all";


    }

    @PostMapping("/update")
    public String edit(@Valid T entity, BindingResult result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        baseService.edit((ID) entity.getClass().getMethod("getId").invoke(entity), entity);
        return "redirect:/"+ title_list + "/all";

    }

    @PostMapping("/delete")
    public String delete(@RequestBody ArrayList<ID> ids) {
        for (ID id : ids) {
            baseService.delete(id);
        }
        return "redirect:/"+ title_list + "/all";

    }

}
