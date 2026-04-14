<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <link rel="stylesheet" type="text/css" href="/css/login.css">
</head>
<body>
<div class="card">
    <h2>Chamados Condomínio</h2>
    <form action="/perform_login" method="post">
        <div class="form-group">
            <label for="username">E-mail</label>
            <input type="email" id="username" name="username" placeholder="seu@email.com" required />
        </div>
        <div class="form-group">
            <label for="password">Senha</label>
            <input type="password" id="password" name="password" placeholder="••••••••" required />
        </div>
        <button type="submit">Entrar</button>
        <% if (request.getParameter("error") != null) { %>
        <p class="error">Email ou senha incorretos.</p>
        <% } %>
    </form>
</div>
</body>
</html>