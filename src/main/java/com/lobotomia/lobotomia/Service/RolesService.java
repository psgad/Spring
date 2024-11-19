package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Roles;
import com.lobotomia.lobotomia.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class RolesService extends BaseService<Roles, UUID> {
    @Autowired
    public RolesService(JpaRepository<Roles, UUID> repository) {
        super(repository);
    }

}
