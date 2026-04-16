<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<!DOCTYPE html>
<html>
<head>
  <meta charset="UTF-8">
  <title>Detalhes do Chamado #${call.id}</title>
  <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<c:if test="${not empty successMessage}">
  <div class="alert alert-success">
    ${successMessage}
  </div>
</c:if>

<c:if test="${not empty errorMessage}">
  <div class="alert alert-danger">
    ${errorMessage}
  </div>
</c:if>

<div class="main-layout">

  <div class="left-column">
    <div class="header">
      <h2>Chamado #${call.id}</h2>
      <c:choose>
        <c:when test="${call.status.isFinal}">
          <span class="status-badge status-final">${call.status.name}</span>
        </c:when>
        <c:otherwise>
          <span class="status-badge status-open">${call.status.name}</span>
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
        <div class="detail-value">${call.callType.title}</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Solicitante:</div>
        <div class="detail-value">${call.user.name} (${call.user.email})</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Localização:</div>
        <div class="detail-value">Bloco ${block.identity} - Unidade ${call.unit.identifier}</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Data de Abertura:</div>
        <div class="detail-value">${call.createdAt}</div>
      </div>

      <div class="detail-row">
        <div class="detail-label">Prazo:</div>
        <div class="detail-value">${call.deadline}</div>
      </div>

      <div class="detail-row" style="flex-direction: column; border-bottom: none;">
        <div class="detail-label" style="width: 100%; margin-bottom: 0.5rem;">Descrição do Problema:</div>
        <div class="description-box">${call.description}</div>
      </div>

      <div class="annexes-section">
        <h3 style="font-size: 1.1rem; color: #374151;">Anexos</h3>
        <c:choose>
          <c:when test="${empty annexes}">
            <p style="color: #94a3b8; font-style: italic; font-size: 0.9rem;">Nenhum arquivo anexado.</p>
          </c:when>
          <c:otherwise>
            <c:forEach var="file" items="${annexes}">
              <div class="annex-item">
                <span style="margin-right: 8px;">📎</span>
                <a href="/download/${file.id}" target="_blank" style="color: #2563eb; text-decoration: none;">
                    ${file.fileName}
                </a>
              </div>
            </c:forEach>
          </c:otherwise>
        </c:choose>
      </div>

      <div style="margin-top: 2rem; display: flex; gap: 1rem;">
        <a href="/calls" class="btn-voltar">&larr; Voltar para a Lista</a>
        <c:if test="${!call.status.isFinal}">
          <a href="/calls/${call.id}/edit" class="btn" style="background-color: #f59e0b;">Editar Chamado</a>
        </c:if>
      </div>
    </div>
  </div>

  <div class="right-column">
    <div class="comments-container">
      <h3 style="margin: 0; font-size: 1.2rem;">Comentários</h3>

      <div class="comments-list">
        <c:forEach var="comment" items="${comments}">
          <div class="comment-card">
            <div class="comment-meta">
              <span>${comment.user.name}<span> #${comment.user.role}</span></span>
              <span style="font-weight: normal; color: #9ca3af;">${comment.createdAt}</span>
            </div>
            <div class="comment-content">
                ${comment.content}
            </div>
          </div>
        </c:forEach>

        <c:if test="${empty comments}">
          <p style="text-align: center; color: #94a3b8; font-size: 0.85rem;">Sem comentários registrados.</p>
        </c:if>
      </div>

      <c:if test="${!call.status.isFinal}">
        <form action="/calls/${call.id}/comments" method="POST" style="border-top: 1px solid #f3f4f6; padding-top: 1rem;">
          <textarea name="content" rows="3" placeholder="Escreva uma mensagem..."
                    style="width: 100%; padding: 8px; border: 1px solid #d1d5db; border-radius: 6px; resize: none;" required></textarea>
          <button type="submit" class="btn-save" style="width: 100%; margin-top: 0.8rem; padding: 0.7rem;">
            Enviar comentário
          </button>
        </form>
      </c:if>
    </div>
  </div>

</div>

</body>
</html>