<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Login | Condomínio</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body style="padding: 0; margin: 0; max-width: none;"> <div class="login-page">
    <div class="card">
        <h2>Chamados Condomínio</h2>

        <% if (request.getParameter("error") != null) { %>
        <div class="alert alert-danger" style="margin-bottom: 1.5rem; text-align: center;">
            E-mail ou senha incorretos.
        </div>
        <% } %>

        <form action="/perform_login" method="post">
            <div class="form-group">
                <label for="username">E-mail</label>
                <input type="email" id="username" name="username" placeholder="seu@email.com" required />
            </div>

            <div class="form-group">
                <label for="password">Senha</label>
                <input type="password" id="password" name="password" placeholder="••••••••" required />
            </div>

            <button type="submit" class="btn-save" style="width: 100%; padding: 0.8rem; font-size: 1rem; margin-top: 1rem;">
                Entrar no Sistema
            </button>
        </form>

        <p style="text-align: center; margin-top: 1.5rem; font-size: 0.85rem; color: var(--text-secondary);">
            Esqueceu sua senha? Entre em contato com a administração.
        </p>
    </div>
</div>

</body>
</html>