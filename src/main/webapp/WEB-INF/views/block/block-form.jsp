<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Novo Bloco | Condomínio</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css" />
  <style>
    /* Pequeno ajuste para garantir que o card de formulário não fique largo demais */
    .form-container {
      max-width: 500px;
      margin: 2rem auto;
    }
  </style>
</head>
<body>

<div class="header">
  <h2>Cadastrar Novo Bloco</h2>
</div>

<div class="form-container card">
  <form action="/blocks" method="post">

    <div class="form-group">
      <label for="identity">Identificação do Bloco</label>
      <input type="text" id="identity" name="identity" placeholder="Ex: A, Sul..." required />
      <small style="color: #6b7280; font-size: 0.75rem;">Nome único para identificar o bloco.</small>
    </div>

    <div class="form-group">
      <label for="qtdFloors">Quantidade de Andares</label>
      <input type="number" id="qtdFloors" name="qtdFloors" min="1" placeholder="0" required />
    </div>

    <div class="form-group">
      <label for="nUnitsPerFloor">Unidades por Andar</label>
      <input type="number" id="nUnitsPerFloor" name="nUnitsPerFloor" min="1" placeholder="0" required />
    </div>

    <div class="actions" style="margin-top: 2rem; display: flex; gap: 1rem; justify-content: flex-end;">
      <a href="/blocks" class="btn-voltar" style="text-decoration: none; line-height: 2.5;">Cancelar</a>
      <button type="submit" class="btn-save">Criar Bloco</button>
    </div>

  </form>
</div>

<div style="text-align: center; margin-top: 1rem;">
  <p style="font-size: 0.85rem; color: #6b7280;">
    * O sistema gerará as unidades automaticamente com base nestes números.
  </p>
</div>

</body>
</html>