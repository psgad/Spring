package com.lobotomia.lobotomia.Repository;

import com.lobotomia.lobotomia.Model.Student;
import org.springframework.data.jpa.repository.JpaRepository;

public interface StudentRepository extends JpaRepository<Student, Long> {
}
