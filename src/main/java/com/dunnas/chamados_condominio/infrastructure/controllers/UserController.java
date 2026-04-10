package com.dunnas.chamados_condominio.infrastructure.controllers;

import com.dunnas.chamados_condominio.application.usecases.CreateUser;
import com.dunnas.chamados_condominio.application.usecases.FindAllUsers;
import com.dunnas.chamados_condominio.application.usecases.FindUserByEmail;
import com.dunnas.chamados_condominio.application.usecases.FindUserById;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final CreateUser createUser;
    private final FindUserByEmail findUserByEmail;
    private final FindUserById findUserById;
    private final UserDTOMapper userDTOMapper;
    private final FindAllUsers findAllUsers;

    public UserController(CreateUser createUser, FindUserByEmail findUserByEmail, FindUserById findUserById, UserDTOMapper userDTOMapper, FindAllUsers findAllUsers) {
        this.createUser = createUser;
        this.findUserByEmail = findUserByEmail;
        this.findUserById = findUserById;
        this.userDTOMapper = userDTOMapper;
        this.findAllUsers = findAllUsers;
    }

    @PostMapping
    public CreateUserResponse createUser(@RequestBody CreateUserRequest request) {
        User newUser = userDTOMapper.toEntity(request);
        User createdUser = createUser.createUser(newUser);
        return userDTOMapper.toResponse(createdUser);
    }

    @GetMapping
    public ResponseEntity<?> findUserByEmail(@RequestParam(required=false) String email) {
        if (email != null) {
            User foundUser = findUserByEmail.findUserByEmail(email);
            return ResponseEntity.ok(userDTOMapper.toResponse(foundUser));
        }
        List<User> users = findAllUsers.findAllUsers();
        return ResponseEntity.ok(users.stream().map(userDTOMapper::toResponse).toList());
    }

    @GetMapping("/{id}")
    public CreateUserResponse findUserById(@PathVariable Long id) {
        User foundUser = findUserById.findUserById(id);
        return userDTOMapper.toResponse(foundUser);
    }

}
