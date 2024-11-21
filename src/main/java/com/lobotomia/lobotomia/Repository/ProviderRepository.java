package com.lobotomia.lobotomia.Repository;

import org.springframework.data.jpa.repository.JpaRepository;
import com.lobotomia.lobotomia.Model.Providers;

import java.util.UUID;

public interface ProviderRepository extends JpaRepository<Providers, UUID> {

}
