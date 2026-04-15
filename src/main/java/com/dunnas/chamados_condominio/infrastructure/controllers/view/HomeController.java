package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.user.FindUserByEmail;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class HomeController {

    private final FindUserByEmail findUserByEmail;

    public HomeController(FindUserByEmail findUserByEmail) {
        this.findUserByEmail = findUserByEmail;
    }

    @GetMapping("/home")
    public String home(Model model, Authentication authentication) {

        if (authentication == null || !authentication.isAuthenticated() || authentication.getName().equals("anonymousUser")) {
            return "/login";
        }

        String email = authentication.getName();
        User user = findUserByEmail.findUserByEmail(email);
        model.addAttribute("user", user);

        return "home";
    }
}
