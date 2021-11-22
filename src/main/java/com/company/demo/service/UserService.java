package com.company.demo.service;

import com.company.demo.controller.request.CreateUserRequest;
import com.company.demo.controller.request.UpdateUserRequest;
import com.company.demo.models.User;
import com.company.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    @Transactional(readOnly = true)
    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }

    @Transactional(readOnly = true)
    public User getUserById(Long id) {
        return userRepository.findById(id).get();
    }

    @Transactional
    public User createUser(CreateUserRequest userRequest) {
        User user = new User();
        user.setFirstname(userRequest.getFirstname());
        user.setLastname(userRequest.getLastname());
        user.setPassport(userRequest.getPassport());
        return saveUser(user);

    }

    @Transactional
    public User updateUser(Long Id, UpdateUserRequest updateUserRequest) {
        User user = getUserById(Id);
        user.setFirstname(updateUserRequest.getFirstname());
        user.setLastname(updateUserRequest.getLastname());
        user.setPassport(updateUserRequest.getPassport());
        return saveUser(user);
    }

    @Transactional
    public User saveUser(User user) {
        return userRepository.save(user);
    }


}
