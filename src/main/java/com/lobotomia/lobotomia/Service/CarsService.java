package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Cars;
import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Repository.CarsRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.UUID;
import java.util.stream.Collectors;

@Service
public class CarsService extends BaseService<Cars, UUID> {
    @Autowired
    CarsRepository carsRepository;

    @Autowired
    public CarsService(JpaRepository<Cars, UUID> repository) {
        super(repository);
    }

    @Override
    public Pagination<Cars> findAll(int page) {
        return new Pagination<Cars>(
                new ArrayList<>(carsRepository
                        .findAll()
                        .stream()
                        .filter(car -> !car.isDeleted()).collect(Collectors.toCollection(ArrayList::new))),
                page);
    }

    @Override
    public void delete(UUID id) {
        if (carsRepository.existsById(id)) {
            Cars car = carsRepository.getReferenceById(id);
            if (car.getCount() == 1)
                car.setDeleted(true);
            else if (car.getCount() > 1) {
                car.setCount(car.getCount() - 1);
                carsRepository.save(car);
            }
        }
    }
}
