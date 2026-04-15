<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Detalhes do Chamado #${call.id}</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
  <style>
    .detail-container {
      background: white;
      padding: 2rem;
      border-radius: 8px;
      box-shadow: 0 4px 6px -1px rgba(0, 0, 0, 0.1);
      margin-top: 1rem;
    }
    .detail-row {
      display: flex;
      border-bottom: 1px solid #f3f4f6;
      padding: 1rem 0;
    }
    .detail-label {
      width: 200px;
      font-weight: 600;
      color: #6b7280;
    }
    .detail-value {
      flex: 1;
      color: #111827;
    }
    .description-box {
      background: #f9fafb;
      padding: 1rem;
      border-radius: 4px;
      margin-top: 0.5rem;
      border: 1px solid #e5e7eb;
      white-space: pre-wrap; /* Mantém as quebras de linha do texto */
    }
  </style>
</head>
<body>

<div class="header">
  <h2>Chamado #${call.id}</h2>
  <c:choose>
    <c:when test="${status.isFinal}">
      <span class="status-badge status-final">${status.name}</span>
    </c:when>
    <c:otherwise>
      <span class="status-badge status-open">${status.name}</span>
    </c:otherwise>
  </c:choose>
</div>

<div class="detail-container">
  <div class="detail-row">
    <div class="detail-label">Título:</div>
    <div class="detail-value"><strong>${call.title}</strong></div>
  </div>

  <div class="detail-row">
    <div class="detail-label">Tipo de Chamado:</div>
    <div class="detail-value">${callType.title}</div>
  </div>

  <div class="detail-row">
    <div class="detail-label">Solicitante:</div>
    <div class="detail-value">${user.name} (${user.email})</div>
  </div>

  <div class="detail-row">
    <div class="detail-label">Localização:</div>
    <div class="detail-value">Bloco ${block.identity} - Unidade ${unit.identifier}</div>
  </div>

  <div class="detail-row">
    <div class="detail-label">Data de Abertura:</div>
    <div class="detail-value">${call.createdAt}</div>
  </div>

  <div class="detail-row">
    <div class="detail-label">Prazo:</div>
    <div class="detail-value">${call.deadline}</div>
  </div>

<div class="detail-row">
  <div class="detail-row" style="flex-direction: column; border-bottom: none;">
    <div class="detail-label" style="width: 100%; margin-bottom: 0.5rem;">Descrição do Problema:</div>
    <div class="description-box">${call.description}</div>
  </div>
</div>

<div style="margin-top: 2rem; display: flex; gap: 1rem;">
  <a href="/calls" class="btn-voltar">&larr; Voltar para a Lista</a>

  <c:if test="${!status.isFinal}">
    <a href="/calls/${call.id}/edit" class="btn" style="background-color: #f59e0b;">Editar Chamado</a>
  </c:if>
</div>
</div>

</body>
</html>