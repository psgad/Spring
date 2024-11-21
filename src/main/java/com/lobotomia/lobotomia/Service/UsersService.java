package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class UsersService extends BaseService<Users, UUID> {
    @Autowired
    public UsersService(JpaRepository<Users, UUID> repository) {
        super(repository);
    }
}
