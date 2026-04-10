package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.application.usecases.CreateUser;
import com.dunnas.chamados_condominio.application.usecases.FindUserByEmail;
import com.dunnas.chamados_condominio.application.usecases.FindUserById;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("users")
public class UserController {
    private final CreateUser createUser;
    private final FindUserByEmail findUserByEmail;
    private final FindUserById findUserById;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUser createUser, FindUserByEmail findUserByEmail, FindUserById findUserById, UserDTOMapper userDTOMapper) {
        this.createUser = createUser;
        this.findUserByEmail = findUserByEmail;
        this.findUserById = findUserById;
        this.userDTOMapper = userDTOMapper;
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        User newUser = userDTOMapper.toEntity(request);
        User createdUser = createUser.createUser(newUser);
        return userDTOMapper.toResponse(createdUser);
    }

    @GetMapping
    public CreateUserResponse findUserByEmail(@RequestParam String email) {
        User foundUser = findUserByEmail.findUserByEmail(email);
        return userDTOMapper.toResponse(foundUser);
    }

    @GetMapping("/{id}")
    public CreateUserResponse findUserById(@PathVariable Long id) {
        User foundUser = findUserById.findUserById(id);
        return userDTOMapper.toResponse(foundUser);
    }

}
