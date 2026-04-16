package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.calltype.FindAllCallTypes;
import com.dunnas.chamados_condominio.application.usecases.calltype.FindCallTypeById;
import com.dunnas.chamados_condominio.application.usecases.user.*;
import com.dunnas.chamados_condominio.domain.entity.CallType;
import com.dunnas.chamados_condominio.domain.entity.User;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserDTOMapper;
import com.dunnas.chamados_condominio.infrastructure.controllers.api.user.UserRequest;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Controller
@RequestMapping("users")
public class UserViewController {
    private final CreateUser createUser;
    private final FindAllUsers findAllUsers;
    private final FindUserByEmail findUserByEmail;
    private final UpdateUser updateUser;
    private final DeleteUser deleteUser;
    private final UserDTOMapper userDTOMapper;
    private final FindAllCallTypes findAllCallTypes;
    private final FindCallTypeById findCallTypeById;

    public UserViewController(CreateUser createUser, FindAllUsers findAllUsers, FindUserByEmail findUserByEmail, UpdateUser updateUser, DeleteUser deleteUser, UserDTOMapper userDTOMapper, FindAllCallTypes findAllCallTypes, FindCallTypeById findCallTypeById) {
        this.createUser = createUser;
        this.findAllUsers = findAllUsers;
        this.findUserByEmail = findUserByEmail;
        this.updateUser = updateUser;
        this.deleteUser = deleteUser;
        this.userDTOMapper = userDTOMapper;
        this.findAllCallTypes = findAllCallTypes;
        this.findCallTypeById = findCallTypeById;
    }

    @GetMapping("/new")
    public String formCreateUser(Model model) {
        List<CallType> calltypes = findAllCallTypes.findAllCallTypes();
        model.addAttribute("callTypes", calltypes);

        return "user/user-form";
    }

    @PostMapping
    public String createUser(@ModelAttribute UserRequest request, @RequestParam("callTypeId") Long callTypeId) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();
        CallType callType = findCallTypeById.findCallTypeById(callTypeId);

        User user = userDTOMapper.toEntity(request);
        user.setScope(callType.getTitle());
        createUser.createUser(user, loggedUserEmail);

        return "redirect:/users";
    }

    @GetMapping
    public String listUsers(Model model) {
        String email = SecurityContextHolder.getContext().getAuthentication().getName();
        User userLogged = findUserByEmail.findUserByEmail(email);
        model.addAttribute("userLogged", userLogged);

        List<User> users = findAllUsers.findAllUsers();
        model.addAttribute("users", users);

        return "user/users";
    }

    @GetMapping("/{id}/edit")
    public String editForm(@PathVariable Long id, Model model) {
        User user = findAllUsers.findAllUsers()
                .stream()
                .filter(u -> u.getId().equals(id))
                .findFirst()
                .orElseThrow();

        model.addAttribute("user", user);

        return "user/user-edit";
    }

    @PostMapping("/{id}")
    public String updateUser(@PathVariable Long id, @ModelAttribute UserRequest request, Model model) {

        String loggedUserEmail = SecurityContextHolder.getContext().getAuthentication().getName();

        User user = userDTOMapper.toEntity(request);

        updateUser.updateUser(id, user, loggedUserEmail);

        return "redirect:/users";
    }

    @PostMapping("/{id}/delete")
    public String deleteUser(@PathVariable Long id) {
        String loggedUserEmail = SecurityContextHolder.getContext()
                .getAuthentication().getName();

        deleteUser.deleteUser(id, loggedUserEmail);

        return "redirect:/users";
    }
}
