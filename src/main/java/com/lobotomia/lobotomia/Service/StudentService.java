package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Student;
import com.lobotomia.lobotomia.Model.Users;

import java.util.List;

public interface StudentService {
    public List<Student> findAllStudent();

    public Student findStudentById(Long id);

    public Student addStudent(Student user);

    public Student editStudent(Student user);

    public void deleteStudent(Long id);

}
