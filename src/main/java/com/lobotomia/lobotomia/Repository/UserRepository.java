package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.Users;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;


public interface UserRepository extends JpaRepository<Users, UUID> {
}