package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Model.Users;
import com.lobotomia.lobotomia.Repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

public interface UserService {
    public List<Users> findAllUsers();

    public Users findUserById(Long id);

    public Users addUser(Users user);

    public Users editUser(Users user);

    public void deleteUser(Long id);


}