package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Student;
import com.lobotomia.lobotomia.Model.Users;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class StudentService extends BaseService<Student, Long>{
    @Autowired
    public StudentService(JpaRepository<Student, Long> repository) {
        super(repository);
    }
}
