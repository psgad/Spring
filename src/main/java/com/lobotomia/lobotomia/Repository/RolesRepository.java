package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.Roles;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RolesRepository extends JpaRepository<Roles, UUID> {
}
