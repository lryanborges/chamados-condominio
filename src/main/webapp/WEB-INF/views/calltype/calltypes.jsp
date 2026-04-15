<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
  <meta charset="UTF-8">
  <title>Tipos de Chamado</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<div class="header">
  <h2>Configurações de Tipos de Chamado</h2>
</div>

<div class="form-container" style="max-width: 1000px; display: flex; gap: 2rem; align-items: flex-start;">

  <div style="flex: 1;">
    <h3 style="margin-bottom: 1rem; color: #333;">Tipos Cadastrados</h3>
    <table style="width: 100%;">
      <thead>
      <tr>
        <th>ID</th>
        <th>Título</th>
        <th>Prazo (Horas)</th>
      </tr>
      </thead>
      <tbody>
      <c:choose>
        <c:when test="${empty callTypes}">
          <tr>
            <td colspan="3" class="empty">Nenhum tipo de chamado cadastrado.</td>
          </tr>
        </c:when>
        <c:otherwise>
          <c:forEach var="type" items="${callTypes}">
            <tr>
              <td>#${type.id}</td>
              <td><strong>${type.title}</strong></td>
              <td>
                <span class="status-badge status-aberto">${type.deadline}h</span>
              </td>
            </tr>
          </c:forEach>
        </c:otherwise>
      </c:choose>
      </tbody>
    </table>
  </div>

  <div style="flex: 0 0 350px; background: #f8f9fa; padding: 1.5rem; border-radius: 8px; border: 1px solid #e9ecef;">
    <h3 style="margin-bottom: 1rem; color: #333;">Novo Tipo</h3>

    <form action="/calltypes" method="POST">
      <div class="form-group">
        <label for="title">Título do Tipo</label>
        <input type="text" id="title" name="title" placeholder="Ex: Elétrica, Hidráulica..." required>
      </div>

      <div class="form-group">
        <label for="deadline">Prazo Estimado (em horas)</label>
        <input type="number" id="deadline" name="deadline" step="0.1" min="0.5" placeholder="Ex: 4.5" required>
      </div>

      <small style="color: #666; display: block; margin-top: -10px; margin-bottom: 1.5rem;">
        Use ponto para decimais (ex: 1.5 para 1h30min).
      </small>

      <button type="submit" class="btn" style="width: 100%;">Salvar Tipo</button>
    </form>
  </div>

</div>

<div style="margin-top: 2rem; margin-left: auto; margin-right: auto; max-width: 1000px;">
  <a href="/calls" class="btn-voltar">&larr; Voltar para Chamados</a>
</div>

</body>
</html>