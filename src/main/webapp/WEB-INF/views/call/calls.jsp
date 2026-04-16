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

<div class="filter-section">
  <form action="/calls" method="GET" id="filterForm">
    <label>Filtrar por Status:</label>
    <select name="statusId" onchange="this.form.submit()">
      <option value="">Todos os Chamados</option>
      <c:forEach var="s" items="${status}">
        <option value="${s.id}" ${param.statusId == s.id ? 'selected' : ''}>
            ${s.name}
        </option>
      </c:forEach>
    </select>
  </form>
</div>

<c:if test="${not empty errorMessage}">
  <div style="background-color: #fee2e2; color: #991b1b; padding: 1rem; border-radius: 8px; margin-bottom: 1.5rem; border: 1px solid #991b1b; font-weight: 600;">
    ${errorMessage}
  </div>
</c:if>

<c:if test="${not empty successMessage}">
  <div style="background-color: #d1fae5; color: #065f46; padding: 1rem; border-radius: 8px; margin-bottom: 1.5rem; border: 1px solid #065f46; font-weight: 600;">
    ${successMessage}
  </div>
</c:if>

<table>
  <thead>
  <tr>
    <th>ID</th>
    <th>Título</th>
    <th>Local (Morador/Unid)</th>
    <th>Tipo de Chamado</th>
    <th>Status</th>
    <th>Data de Criação</th>
    <th>Deadline</th>
    <th>Data de Finalização</th>
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
          <td>Morador ID ${call.user.name} - Ap. ${call.unit.identifier}</td>
          <td>${call.callType.title}</td>
          <td>
            <c:choose>
              <c:when test="${loggedUser.role == 'COLLABORATOR' || loggedUser.role == 'ADMIN'}">
                <form action="/calls/${call.id}/status" method="POST" style="display: flex; gap: 4px; align-items: center;">
                  <select name="statusId" style="padding: 4px; border: 1px solid #d1d5db; border-radius: 4px; font-size: 0.85rem;">
                    <c:forEach var="st" items="${status}">
                      <option value="${st.id}" ${st.id == call.status.id ? 'selected' : ''}>
                          ${st.name}
                      </option>
                    </c:forEach>
                  </select>
                  <button type="submit" class="btn-save" style="padding: 4px 8px; font-size: 0.75rem;">
                    Salvar
                  </button>
                </form>
              </c:when>

              <c:otherwise>
                <span class="status-badge ${call.status.isFinal ? 'status-closed' : 'status-open'}">
                    ${call.status.name}
                </span>
              </c:otherwise>
            </c:choose>
          </td>
          <td>${call.createdAt}</td>
          <td>${call.deadline}</td>
          <td>${call.finishedAt}</td>
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