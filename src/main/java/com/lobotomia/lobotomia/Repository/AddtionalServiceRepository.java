package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.AdditionalServices;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface AddtionalServiceRepository extends JpaRepository<AdditionalServices, UUID> {

}
