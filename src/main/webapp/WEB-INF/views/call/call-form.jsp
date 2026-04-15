<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Abrir Novo Chamado</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<div class="header">
  <h2>Novo Chamado</h2>
</div>

<div class="form-container">
  <form action="/calls" method="POST">
    <div class="form-group">
      <label for="title">Título do Problema</label>
      <input type="text" id="title" name="title" placeholder="Ex: Vazamento na pia" required>
    </div>

    <div class="form-group">
      <label for="unitId">Unidade Relacionada</label>
      <select id="unitId" name="unitId" required>
        <option value="">Selecione a unidade...</option>
        <!-- <c:forEach var="unit" items="${userUnits}">
          <option value="${unit.id}">Bloco ${unit.blockIdentity} - Ap. ${unit.identifier}</option>
        </c:forEach> -->
      </select>
    </div>

    <div class="form-group">
      <label for="callTypeId">Tipo de Manutenção</label>
      <select id="callTypeId" name="callTypeId" required>
        <option value="">Selecione o tipo...</option>
        <c:forEach var="calltype" items="${callTypes}">
          <option value="${calltype.id}">${calltype.title}</option>
        </c:forEach>
      </select>
    </div>

    <div class="form-group">
      <label for="description">Descrição Detalhada</label>
      <textarea id="description" name="description" placeholder="Descreva o que está acontecendo..." required></textarea>
    </div>

    <div style="display: flex; gap: 1rem; margin-top: 1rem;">
      <button type="submit" class="btn">Abrir Chamado</button>
      <a href="/calls" class="btn-voltar" style="text-decoration: none; text-align: center;">Cancelar</a>
    </div>
  </form>
</div>

</body>
</html>