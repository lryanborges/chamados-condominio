<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Lista de Usuários</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<div class="header">
    <h2>Lista de Usuários</h2>
    <c:if test="${userLogged.role == 'ADMIN'}">
        <a href="/users/new" class="btn">+ Criar Usuário</a>
    </c:if>
</div>

<c:if test="${not empty mensagemSucesso}">
    <div class="alert alert-success">${mensagemSucesso}</div>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Role</th>
        <th>Scope</th>
        <c:if test="${userLogged.role == 'ADMIN'}">
            <th style="text-align: center;">Ações</th>
        </c:if>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty users}">
            <tr>
                <td colspan="6" class="empty">Nenhum usuário encontrado.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="user" items="${users}">
                <tr>
                    <td>#${user.id}</td>
                    <td><strong>${user.name}</strong></td>
                    <td>${user.email}</td>
                    <td><span class="status-badge status-progresso">${user.role}</span></td>
                    <td>${user.scope}</td>
                    <c:if test="${userLogged.role == 'ADMIN'}">
                        <td>
                            <div style="display: flex; gap: 0.5rem; justify-content: center;">
                                <a href="/users/${user.id}/edit" class="btn-detalhes">Editar</a>

                                <form action="/users/${user.id}/delete" method="post"
                                      onsubmit="return confirm('Tem certeza que deseja excluir?');"
                                      class="form-delete">
                                    <button type="submit" class="btn-delete"
                                            style="padding: 4px 8px; font-size: 0.8rem; cursor: pointer;">
                                        Excluir
                                    </button>
                                </form>
                            </div>
                        </td>
                    </c:if>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<div style="margin-top: 2rem;">
    <a href="/home" class="btn-voltar">&larr; Voltar ao Painel</a>
</div>

</body>
</html>