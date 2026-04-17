<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Criar Usuário</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <style>
        .error-alert {
            background-color: #fee2e2;
            color: #b91c1c;
            padding: 1rem;
            border-radius: 0.5rem;
            margin-bottom: 1rem;
            border: 1px solid #fecaca;
        }
    </style>
</head>
<body>

<div class="header">
    <h2>Criar Usuário</h2>
</div>

<div class="form-container">

    <c:if test="${not empty errorMessage}">
        <div class="error-alert">
                ${errorMessage}
        </div>
    </c:if>

    <form action="/users" method="POST">

        <div class="form-group">
            <label for="name">Nome Completo</label>
            <input type="text" id="name" name="name" placeholder="Ex: João Silva" required />
        </div>

        <div class="form-group">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" placeholder="exemplo@email.com" required />
        </div>

        <div class="form-group">
            <label for="password">Senha</label>
            <input type="password" id="password" name="password" required />
        </div>

        <div class="form-group">
            <label for="confirmPassword">Confirmar Senha</label>
            <input type="password" id="confirmPassword" name="confirmPassword" required />
        </div>

        <div class="form-group">
            <label for="role">Função (Role)</label>
            <select name="role" id="role" required>
                <option value="" disabled selected>Selecione uma função...</option>
                <option value="ADMIN">Administrador</option>
                <option value="RESIDENT">Morador</option>
                <option value="COLLABORATOR">Colaborador</option>
            </select>
        </div>

        <div class="form-group">
            <label for="callType">Escopo (Opcional)</label>
            <select id="callType" name="callTypeId" class="form-control">
                <option value="">Nenhum escopo selecionado</option>
                <c:forEach var="type" items="${callTypes}">
                    <option value="${type.id}">${type.title}</option>
                </c:forEach>
            </select>
        </div>

        <div style="display: flex; gap: 1rem; margin-top: 1rem;">
            <button type="submit" class="btn-save">Salvar Usuário</button>
            <a href="/users" class="btn-voltar" style="text-decoration: none; text-align: center;">Cancelar</a>
        </div>

    </form>
</div>

<script>
    const form = document.querySelector('form');
    const password = document.getElementById('password');
    const confirm = document.getElementById('confirmPassword');

    form.addEventListener('submit', (e) => {
        if (password.value !== confirm.value) {
            e.preventDefault();
            alert("As senhas não coincidem!");
        }
    });
</script>

</body>
</html>