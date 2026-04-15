<%@ page contentType="text/html;charset=UTF-8" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <title>Home</title>
    <style>
        body {
            font-family: Arial;
            background: #f4f6f9;
            display: flex;
            justify-content: center;
            align-items: center;
            height: 100vh;
        }

        .card {
            background: white;
            padding: 2rem;
            border-radius: 8px;
            box-shadow: 0 2px 10px rgba(0,0,0,0.1);
            width: 400px;
        }

        h2 {
            margin-bottom: 1rem;
        }

        p {
            margin: 0.5rem 0;
        }

        .logout {
            margin-top: 1.5rem;
        }

        button {
            padding: 0.6rem 1rem;
            border: none;
            background: #e11d48;
            color: white;
            border-radius: 4px;
            cursor: pointer;
        }

        button:hover {
            background: #be123c;
        }
    </style>
</head>
<body>

<div class="card">
    <h2>Bem-vindo 👋</h2>

    <p><strong>Usuário:</strong> ${user.name}</p>
    <p><strong>Role:</strong> ${user.role}</p>
    <p><strong>Scopes:</strong> ${user.scope}</p>

    <div class="logout">
        <form action="/logout" method="post">
            <button type="submit">Sair</button>
        </form>
    </div>

    <c:if test="${user.role == 'ADMIN'}">
        <a href="/users">
            <button>Gerenciar usuários</button>
        </a>
    </c:if>
</div>

</body>
</html>