package com.company.demo.service;

import com.company.demo.models.User;
import com.company.demo.repository.UserRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
@RequiredArgsConstructor
public class UserService {

    private final UserRepository userRepository;

    public Page<User> getUsers(Pageable pageable) {
        return userRepository.findAll(pageable);
    }
    public User getUserById(Long id){ return userRepository.findById(id).get(); }
    public User saveUser(User user){return userRepository.save(user);}


    }



