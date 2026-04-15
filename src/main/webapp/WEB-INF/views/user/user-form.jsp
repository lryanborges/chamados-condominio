<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<h2>Criar Usuário</h2>

<form action="/users" method="post">

    <label>Nome</label>
    <input type="text" name="name" required />

    <label>Email</label>
    <input type="email" name="email" required />

    <label>Senha</label>
    <input type="password" name="password" required />

    <label>Role</label>
    <select name="role" id="role" required>
        <option value="" disabled selected>Selecione uma função</option>
        <option value="ADMIN">Administrador</option>
        <option value="RESIDENT">Morador</option>
        <option value="COLLABORATOR">Colaborador</option>
    </select>

    <label>Scope</label>
    <input type="text" name="scope"/>

    <button type="submit">Salvar</button>

</form>

<a href="/users">
    <button type="button">Voltar</button>
</a>