<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Lista de usuários</h2>

<table border="1" cellpadding="10" cellspacing="0" style="width:100%; border-collapse: collapse;">

    <thead>
    <tr>
        <th>ID</th>
        <th>Nome</th>
        <th>Email</th>
        <th>Role</th>
        <th>Scope</th>
        <c:if test="${userLogged.role == 'ADMIN'}">
            <th>Ações</th>
        </c:if>
    </tr>
    </thead>

    <tbody>
    <c:forEach var="user" items="${users}">
        <tr>
            <td>${user.id}</td>
            <td>${user.name}</td>
            <td>${user.email}</td>
            <td>${user.role}</td>
            <td>${user.scope}</td>

            <c:if test="${userLogged.role == 'ADMIN'}">
                <td>
                    <a href="/users/${user.id}/edit">
                        <button type="button">Editar</button>
                    </a>
                <td>
                    <form action="/users/${user.id}/delete" method="post"
                          onsubmit="return confirm('Tem certeza que deseja excluir?');">
                        <button type="submit">Excluir</button>
                    </form>
                </td>
                </td>
            </c:if>

        </tr>
    </c:forEach>
    </tbody>

</table>

<c:if test="${userLogged.role == 'ADMIN'}">
    <a href="/users/new">
        <button>Criar usuário</button>
    </a>
</c:if>

<a href="/home">
    <button type="button">Voltar</button>
</a>