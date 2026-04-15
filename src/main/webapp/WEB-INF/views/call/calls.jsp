<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Lista de Chamados</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
  <style>
    .status-badge {
      padding: 4px 8px;
      border-radius: 12px;
      font-size: 0.8rem;
      font-weight: bold;
      text-transform: uppercase;
    }
    .status-aberto { background: #fef3c7; color: #92400e; }
    .status-progresso { background: #dbeafe; color: #1e40af; }
    .status-concluido { background: #d1fae5; color: #065f46; }
  </style>
</head>
<body>

<div class="header">
  <h2>Chamados do Condomínio</h2>
  <div style="display: flex; gap: 0.5rem;">
    <c:if test="${loggedUser.role == 'ADMIN'}">
      <a href="/calltypes" class="btn-detalhes">+ Novo Tipo de Chamado</a>
      <a href="/status" class="btn-detalhes">+ Novo Status</a>
    </c:if>

    <a href="/calls/new" class="btn">+ Abrir Chamado</a>
  </div>
</div>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Título</th>
    <th>Local (Morador/Unid)</th>
    <th>Tipo de Chamado</th>
    <th>Status</th>
    <th>Data</th>
    <th>Deadline</th>
    <th>Ações</th>
  </tr>
  </thead>
  <tbody>
  <c:choose>
    <c:when test="${empty calls}">
      <tr>
        <td colspan="6" class="empty">Nenhum chamado registrado.</td>
      </tr>
    </c:when>
    <c:otherwise>
      <c:forEach var="call" items="${calls}">
        <tr>
          <td>#${call.id}</td>
          <td><strong>${call.title}</strong></td>
          <td>Morador ID ${call.userId} - Ap. ${call.unitId}</td>
          <td>${call.callTypeId}</td>
          <td>${call.statusId}</td>
          <td>${call.createdAt}</td>
          <td>${call.deadline}</td>
          <td>
            <a href="/calls/${call.id}" class="btn-detalhes">Detalhes</a>
          </td>
        </tr>
      </c:forEach>
    </c:otherwise>
  </c:choose>
  </tbody>
</table>

<div style="margin-top: 2rem;">
  <a href="/home" class="btn-voltar">&larr; Voltar ao Painel</a>
</div>

</body>
</html>