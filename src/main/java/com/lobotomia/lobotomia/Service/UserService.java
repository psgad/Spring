package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Pagination;
import com.lobotomia.lobotomia.Model.User;
import com.lobotomia.lobotomia.Repository.UserRepository;
import jakarta.annotation.Nullable;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.stream.Collectors;

@Service
public interface UserService {
    public List<User> findAllUser();

    public User findUserById(Long id);

    public User addUser(User student);

    public void editUser(User student);

    public void deleteUser(Integer id);

    public HashSet<String> GetAllCategory() {
        HashSet<String> hashSet = new HashSet<>();

        for(People people : .GetAll())
        {
            hashSet.add(people.getLastName());
        }

        return hashSet;
    }

}
