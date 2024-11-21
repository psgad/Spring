package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Providers;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.security.Provider;
import java.util.UUID;

@Service
public class ProviderService extends BaseService<Providers, UUID> {
    @Autowired
    public ProviderService(JpaRepository<Providers, UUID> repository) {
        super(repository);
    }
}
