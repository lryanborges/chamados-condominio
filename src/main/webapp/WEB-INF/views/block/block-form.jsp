<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Novo Bloco</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css" />
</head>
<body>
<div class="card">
  <h2>Novo Bloco</h2>
  <form action="/blocks" method="post">
    <div class="form-group">
      <label for="identity">Identificação</label>
      <input type="text" id="identity" name="identity" placeholder="Ex: Bloco A" required />
    </div>
    <div class="form-group">
      <label for="qtdFloors">Quantidade de Andares</label>
      <input type="number" id="qtdFloors" name="qtdFloors" min="1" required />
    </div>
    <div class="form-group">
      <label for="nUnitsPerFloor">Unidades por Andar</label>
      <input type="number" id="nUnitsPerFloor" name="nUnitsPerFloor" min="1" required />
    </div>
    <div class="actions">
      <a href="/blocks" class="btn btn-secondary">Cancelar</a>
      <button type="submit" class="btn btn-primary">Criar Bloco</button>
    </div>
  </form>
</div>
</body>
</html>