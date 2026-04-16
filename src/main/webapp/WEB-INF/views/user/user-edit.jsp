<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Editar Usuário</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<div class="header">
    <h2>Editar Usuário: ${user.name}</h2>
</div>

<div class="form-container">
    <form action="/users/${user.id}" method="POST">
        <input type="hidden" name="id" value="${user.id}" />

        <div class="form-group">
            <label for="name">Nome Completo</label>
            <input type="text" id="name" name="name" value="${user.name}" required />
        </div>

        <div class="form-group">
            <label for="email">E-mail</label>
            <input type="email" id="email" name="email" value="${user.email}" required />
        </div>

        <div class="form-group">
            <label for="password">Nova Senha</label>
            <input type="password" id="password" name="password" placeholder="Deixe em branco para manter a atual" />
            <small style="color: #666;">Preencha apenas se desejar alterar a senha.</small>
        </div>

        <div class="form-group">
            <label for="role">Função (Role)</label>
            <select name="role" id="role" required>
                <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Administrador</option>
                <option value="RESIDENT" ${user.role == 'RESIDENT' ? 'selected' : ''}>Morador</option>
                <option value="COLLABORATOR" ${user.role == 'COLLABORATOR' ? 'selected' : ''}>Colaborador</option>
            </select>
        </div>

        <div class="form-group">
            <label for="scope">Escopo (Scope)</label>
            <input type="text" id="scope" name="scope" value="${user.scope}" />
        </div>

        <div style="display: flex; gap: 1rem; margin-top: 1rem;">
            <button type="submit" class="btn btn-save">Atualizar Dados</button>
            <a href="/users" class="btn-voltar" style="text-decoration: none; text-align: center;">Cancelar</a>
        </div>

    </form>
</div>

</body>
</html>