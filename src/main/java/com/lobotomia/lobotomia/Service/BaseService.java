package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Model.Users;
import org.springframework.data.domain.Sort;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.security.core.parameters.P;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;


@Service
public abstract class BaseService<T, ID> {

    private final JpaRepository<T, ID> repository;

    public BaseService(JpaRepository<T, ID> repository) {
        this.repository = repository;
    }

    public Pagination<T> findAll(int page) {
        return new Pagination<T>(new ArrayList<>(repository.findAll()), page);
    }

    public List<T> findAll() {
        return repository.findAll(Sort.by("id"));
    }
    public T findById(ID id) {
        return repository.findById(id).orElse(null);
    }

    public T add(T entity) {
        return repository.save(entity);
    }

    public T edit(ID id, T entity) {
        if (repository.existsById(id)) {
            return repository.save(entity);
        }
        return null;
    }

    public void delete(ID id) {
        if (repository.existsById(id))
            repository.deleteById(id);
    }
}
