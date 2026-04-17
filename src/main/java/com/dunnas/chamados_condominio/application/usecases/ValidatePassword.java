package com.dunnas.chamados_condominio.application.usecases;

public class ValidatePassword {

    public Boolean validatePassword(String password, String confirmPassword) {
        return password.equals(confirmPassword);
    }

}
