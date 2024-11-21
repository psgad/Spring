package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.AdditionalServices;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class AdditionalServicesService extends BaseService<AdditionalServices, UUID> {
    @Autowired
    public AdditionalServicesService(JpaRepository<AdditionalServices, UUID> repository) {
        super(repository);
    }
}
