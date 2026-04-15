<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<h2>Editar Usuário</h2>

<form action="/users/${user.id}" method="post">

    <input type="hidden" name="id" value="${user.id}" />

    <label>Nome</label>
    <input type="text" name="name" value="${user.name}" required />

    <label>Email</label>
    <input type="email" name="email" value="${user.email}" required />

    <label>Senha</label>
    <input type="password" name="password" />

    <label>Role</label>
    <select name="role" required>
        <option value="ADMIN" ${user.role == 'ADMIN' ? 'selected' : ''}>Administrador</option>
        <option value="RESIDENT" ${user.role == 'RESIDENT' ? 'selected' : ''}>Morador</option>
        <option value="COLLABORATOR" ${user.role == 'COLLABORATOR' ? 'selected' : ''}>Colaborador</option>
    </select>

    <label>Scope</label>
    <input type="text" name="scope" value="${user.scope}" />

    <button type="submit">Atualizar</button>

</form>

<a href="/users">
    <button type="button">Voltar</button>
</a>