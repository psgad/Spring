package com.lobotomia.lobotomia.Repository;


import com.lobotomia.lobotomia.Model.*;
import org.springframework.data.jpa.repository.JpaRepository;

import java.security.Provider;
import java.util.UUID;

public interface ProfileRepository extends JpaRepository<Profile, UUID> {
    Profile findByUsername(String login);

    boolean existsByUsername(String login);
}




