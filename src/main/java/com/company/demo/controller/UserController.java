package com.company.demo.controller;

import com.company.demo.controller.request.CreateUserRequest;
import com.company.demo.controller.request.UpdateUserRequest;
import com.company.demo.controller.response.UserResponse;
import com.company.demo.models.User;
import com.company.demo.service.UserService;
import ma.glasnost.orika.MapperFacade;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.web.bind.annotation.*;

import javax.validation.constraints.NotNull;
import java.util.function.Function;

@RestController
@RequestMapping(value = "/users")
public class UserController {
    @Autowired
    private UserService userService;
    @Autowired
    private MapperFacade mapper;

    @GetMapping
    public Page<UserResponse> getUsers(Pageable pageable) {
        return userService.getUsers(pageable).map(new Function<User, UserResponse>() {
            @Override
            public UserResponse apply(User user) {
                return mapper.map(user, UserResponse.class);
            }
        });
    }

    @GetMapping(value = "/{id}")
    public UserResponse getUserById(@PathVariable("id") Long Id) {
        return mapper.map(userService.getUserById(Id), UserResponse.class);
    }

    @PostMapping
    public UserResponse createUser(@RequestBody CreateUserRequest newUser){
        User user = userService.createUser(newUser);
        return mapper.map(user, UserResponse.class);
    }

    @PutMapping(value = "/{id}")
    public UserResponse updateUser(@PathVariable("id")  Long Id, @RequestBody UpdateUserRequest updateUser) {
        return mapper.map(userService.updateUser(Id,updateUser), UserResponse.class);
    }

    @PatchMapping(value = "/{id}")
    public void deleteUser(@PathVariable("id") @NotNull Long Id) {
     userService.getUserById(Id).setDeleted(true);
     userService.saveUser(userService.getUserById(Id));
    }
}
