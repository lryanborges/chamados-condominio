<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Login</title>
    <style>
        * { box-sizing: border-box; margin: 0; padding: 0; }

        body {
            font-family: Arial, sans-serif;
            background: #f0f2f5;
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
            width: 100%;
            max-width: 380px;
        }

        h2 {
            text-align: center;
            margin-bottom: 1.5rem;
            color: #333;
        }

        .form-group {
            margin-bottom: 1rem;
        }

        label {
            display: block;
            margin-bottom: 0.4rem;
            color: #555;
            font-size: 0.9rem;
        }

        input {
            width: 100%;
            padding: 0.6rem 0.8rem;
            border: 1px solid #ddd;
            border-radius: 4px;
            font-size: 1rem;
        }

        button {
            width: 100%;
            padding: 0.75rem;
            background: #4f46e5;
            color: white;
            border: none;
            border-radius: 4px;
            font-size: 1rem;
            cursor: pointer;
            margin-top: 0.5rem;
        }

        button:hover { background: #4338ca; }

        .error {
            color: red;
            font-size: 0.85rem;
            text-align: center;
            margin-top: 1rem;
        }
    </style>
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