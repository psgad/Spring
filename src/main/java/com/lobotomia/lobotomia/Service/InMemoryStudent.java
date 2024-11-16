package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Student;
import com.lobotomia.lobotomia.Model.Users;
import com.lobotomia.lobotomia.Repository.StudentRepository;
import com.lobotomia.lobotomia.Repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryStudent implements StudentService {
    private final StudentRepository studentRepository;

    public InMemoryStudent(StudentRepository studentRepository) {
        this.studentRepository = studentRepository;
    }

    @Override
    public List<Student> findAllStudent() {
        return studentRepository.findAll(Sort.by("id"));
    }

    @Override
    public Student findStudentById(Long id) {
        return studentRepository.findById(id).orElse(null);
    }

    @Override
    public Student addStudent(Student user) {
        return studentRepository.save(user);
    }

    @Override
    public Student editStudent(Student user) {
        if (studentRepository.existsById(user.getId())) {
            return studentRepository.save(user);
        }
        return null;
    }

    @Override
    public void deleteStudent(Long id) {
        if (studentRepository.existsById(id)) {
            studentRepository.deleteById(id);
        }
    }
}
