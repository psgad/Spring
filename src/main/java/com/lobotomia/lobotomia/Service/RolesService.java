package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Roles;
import com.lobotomia.lobotomia.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

@Service
public class RolesService extends BaseService<Roles, Long> {
    @Autowired
    public RolesService(JpaRepository<Roles, Long> repository) {
        super(repository);
    }
}
