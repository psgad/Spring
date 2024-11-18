package com.lobotomia.lobotomia.controllers;


import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Service.BaseService;
import jakarta.persistence.Entity;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;

import java.lang.reflect.InvocationTargetException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

@Controller
public class BaseController<T, ID> {
    @Autowired
    public final BaseService<T, ID> baseService;
    public String title_list, title_model;
    public Class<T> cls;

    protected BaseController(BaseService<T, ID> baseService) {
        this.baseService = baseService;
    }


    @GetMapping("/all")
    public String getAll(Model model,
                         @RequestParam(value = "page", required = false, defaultValue = "1") int page) throws InstantiationException, IllegalAccessException {
        Pagination<T> list = baseService.findAll(page);
        model.addAttribute(title_list, list);
        model.addAttribute(title_model, cls.newInstance());
        return title_list;
    }

    @PostMapping("/update")
    public String add(@Valid @ModelAttribute T entity, BindingResult result, Model model) throws InstantiationException, IllegalAccessException {
        if (result.hasErrors()) {
            List<T> list = baseService.findAll();
            model.addAttribute(title_list, list);
            model.addAttribute(title_model, cls.newInstance());
        }
        baseService.add(entity);
        return "redirect:/" + title_list + "/all";
    }

    @PostMapping("/edit")
    public String edit(@Valid @ModelAttribute T entity, BindingResult result) throws NoSuchMethodException, InvocationTargetException, IllegalAccessException {
        baseService.edit((ID) entity.getClass().getMethod("getId").invoke(entity), entity);
        return "redirect:/" + title_list + "/all";
    }
    @DeleteMapping("/delete")
    public String delete(@RequestBody ArrayList<ID> ids) {
        for (ID id : ids) {
            baseService.delete(id);
        }
        return "redirect:/users/all";
    }
}
