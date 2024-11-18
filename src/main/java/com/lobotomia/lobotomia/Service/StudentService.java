package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Student;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.UUID;

@Service
public class StudentService extends BaseService<Student, UUID> {
    @Autowired
    public StudentService(JpaRepository<Student, UUID> repository) {
        super(repository);
    }
}
