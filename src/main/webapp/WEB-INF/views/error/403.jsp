<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <meta name="viewport" content="width=device-width, initial-scale=1.0">
  <title>Acesso Negado | Condomínio</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
  <style>
    body {
      background-color: #f3f4f6;
      display: flex;
      align-items: center;
      justify-content: center;
      height: 100vh;
      margin: 0;
      font-family: 'Inter', system-ui, -apple-system, sans-serif;
    }
    h1 {
      color: #111827;
      font-size: 1.8rem;
      margin-bottom: 0.5rem;
    }
    p {
      color: #4b5563;
      margin-bottom: 2rem;
      line-height: 1.5;
    }
  </style>
</head>
<body>

<div class="error-container">
  <div class="icon-lock">🔒</div>
  <h1>${errorTitle}</h1>

  <p>${errorMessage}</p>

  <a href="/home" class="btn-back">Voltar para o Início</a>

  <span class="status-code">Erro 403 - Forbidden</span>
</div>

</body>
</html>