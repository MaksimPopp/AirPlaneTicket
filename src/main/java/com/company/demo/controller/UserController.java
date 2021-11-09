package com.company.demo.controller;

import com.company.demo.controller.request.CreateUserRequest;
import com.company.demo.controller.request.UpdateUserRequest;
import com.company.demo.models.User;
import com.company.demo.service.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;

    @GetMapping
    public Page<User> getUsers(Pageable pageable) {
        return userService.getUsers(pageable);
    }

    @GetMapping(value = "/{id}")
    public User getUserById(@PathVariable("id") Long Id) {
        return userService.getUserById(Id);
    }

    @PostMapping
    public User createUser(@RequestBody CreateUserRequest newUser){
        User user = new User();
        user.setId(newUser.getId());
        user.setFirstname(newUser.getFirstname());
        user.setLastname(newUser.getLastname());
        user.setPassport(newUser.getPassport());
        user.setTickets(newUser.getTickets());
        user.setDeleted(newUser.isDeleted());
        return userService.saveUser(user);
    }

    @PutMapping(value = "/{id}")
    public User updateUser(@PathVariable("id")  Long Id, @RequestBody UpdateUserRequest updateUser) {
        User user = userService.getUserById(Id);
        user.setFirstname(updateUser.getFirstname());
        user.setLastname(updateUser.getLastname());
        user.setPassport(updateUser.getPassport());
        user.setTickets(updateUser.getTickets());
        return userService.saveUser(user);
    }

    @PatchMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") @NotNull Long Id) {
     userService.getUserById(Id).setDeleted(true);
     userService.saveUser(userService.getUserById(Id));
    }
}
