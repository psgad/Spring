package com.lobotomia.lobotomia.Service;

import com.lobotomia.lobotomia.Model.Users;
import com.lobotomia.lobotomia.Repository.UserRepository;
import org.springframework.data.domain.Sort;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class InMemoryUsers implements UserService{
    private final UserRepository userRepository;

    public InMemoryUsers(UserRepository userRepository) {
        this.userRepository = userRepository;
    }


    @Override
    public List<Users> findAllUsers() {
        return userRepository.findAll(Sort.by("id"));

    }

    @Override
    public Users findUserById(Long id) {
        return userRepository.findById(id).orElse(null);
    }

    @Override
    public Users addUser(Users user) {
        return userRepository.save(user);

    }

    @Override
    public Users editUser(Users user) {
        if (userRepository.existsById(user.getId())) {
            return userRepository.save(user);
        }
        return null;

    }

    @Override
    public void deleteUser(Long id) {
        if (userRepository.existsById(id)) {
            userRepository.deleteById(id);
        }

    }
}
