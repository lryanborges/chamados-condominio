package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.application.usecases.CreateUser;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("users")
public class UserController {
    private final CreateUser createUser;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUser createUser, UserDTOMapper userDTOMapper) {
        this.createUser = createUser;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        User newUser = userDTOMapper.toEntity(request);
        User createdUser = createUser.createUser(newUser);
        return userDTOMapper.toResponse(createdUser);
    }

}
