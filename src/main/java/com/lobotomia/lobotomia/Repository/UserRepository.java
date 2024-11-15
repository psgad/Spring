package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;

public interface UserRepository extends JpaRepository<User, Integer>{
}