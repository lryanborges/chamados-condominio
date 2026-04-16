<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Painel Principal</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<div class="header">
    <h2>Bem-vindo, ${user.name} 👋</h2>
    <form action="/logout" method="post" style="margin: 0;">
        <button type="submit" class="btn-delete">Sair</button>
    </form>
</div>

<div class="form-container" style="max-width: 800px;">
    <div style="margin-bottom: 2rem;">
        <p><strong>Perfil:</strong> <span class="status-badge status-progresso">${user.role}</span></p>
        <p><strong>Escopo de Acesso:</strong> ${user.scope}</p>
    </div>

    <h3 style="margin-bottom: 1rem; color: #333;">Ações Disponíveis</h3>

    <div style="display: grid; grid-template-columns: repeat(auto-fit, minmax(200px, 1fr)); gap: 1rem;">

        <c:if test="${user.role == 'ADMIN'}">
            <a href="/users" class="btn btn-save text-align: center; display: flex; align-items: center; justify-content: center;">
                Gerenciar Usuários
            </a>
            <a href="/blocks" class="btn btn-save text-align: center; display: flex; align-items: center; justify-content: center;">
                Gerenciar Blocos
            </a>
            <a href="/calls" class="btn btn-save text-align: center; display: flex; align-items: center; justify-content: center;">
                Gerenciar Chamados
            </a>
        </c:if>

        <c:if test="${user.role == 'RESIDENT' || user.role == 'COLLABORATOR'}">
            <a href="/calls" class="btn" style="text-decoration: none; text-align: center; display: flex; align-items: center; justify-content: center;">
                Meus Chamados
            </a>
        </c:if>

    </div>
</div>

</body>
</html>