package com.dunnas.chamados_condominio.infrastructure.controllers.user;

import com.dunnas.chamados_condominio.application.usecases.user.*;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("users")
public class UserController {
    private final CreateUser createUser;
    private final FindUserByEmail findUserByEmail;
    private final FindUserById findUserById;
    private final FindAllUsers findAllUsers;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;
    private final LinkUserToUnit linkUserToUnit;
    private final UserDTOMapper userDTOMapper;

    public UserController(CreateUser createUser, FindUserByEmail findUserByEmail, FindUserById findUserById, UserDTOMapper userDTOMapper, FindAllUsers findAllUsers, UpdateUser updateUser, DeleteUser deleteUser, LinkUserToUnit linkUserToUnit) {
        this.createUser = createUser;
        this.findUserByEmail = findUserByEmail;
        this.findUserById = findUserById;
        this.findAllUsers = findAllUsers;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
        this.userDTOMapper = userDTOMapper;
        this.linkUserToUnit = linkUserToUnit;
    }

    @PostMapping
    public UserResponse createUser(@RequestBody UserRequest request) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User newUser = userDTOMapper.toEntity(request);
        User createdUser = createUser.createUser(newUser, loggedUserEmail);
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
    public UserResponse findUserById(@PathVariable Long id) {
        User foundUser = findUserById.findUserById(id);
        return userDTOMapper.toResponse(foundUser);
    }

    @PatchMapping("/{id}")
    public UserResponse updateUser(@PathVariable Long id, @RequestBody UserRequest request) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        User updatedUser = userDTOMapper.toEntity(request);
        User user = updateUser.updateUser(id, updatedUser, loggedUserEmail);
        return userDTOMapper.toResponse(user);
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteUser(@PathVariable Long id) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        deleteUser.deleteUser(id, loggedUserEmail);
        return ResponseEntity.noContent().build();
    }

    @PostMapping("/{userId}/units")
    public ResponseEntity<Void> linkUnitToUser(@PathVariable Long userId, @RequestBody List<Long> unitIds) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        linkUserToUnit.link(userId, unitIds, loggedUserEmail);
        return ResponseEntity.noContent().build();
    }

}
