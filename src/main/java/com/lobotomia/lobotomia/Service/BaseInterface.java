package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Users;

import java.util.List;

public interface BaseInterface<T, ID> {
    public List<T> findAll();

    public T findById(ID id);

    public T add(T user);

    public T edit(T user);

    public void delete(ID id);
}
