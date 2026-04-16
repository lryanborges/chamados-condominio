package com.dunnas.chamados_condominio.infrastructure.controllers.view;

import com.dunnas.chamados_condominio.application.usecases.user.FindUserByEmail;
import com.dunnas.chamados_condominio.domain.entity.User;
import org.springframework.security.core.Authentication;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class AuthController {

    @GetMapping("/login")
    public String loginPage() {
        return "login";
    }

    @GetMapping("/access-denied")
    public String accessDeniedPage(Model model) {
        model.addAttribute("errorTitle", "Acesso Restrito");
        model.addAttribute("errorMessage", "Sua conta não possui as permissões necessárias para acessar este conteúdo.");
        return "error/403";
    }

}
