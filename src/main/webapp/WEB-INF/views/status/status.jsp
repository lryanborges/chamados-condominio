<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html lang="pt-br">
<head>
    <meta charset="UTF-8">
    <title>Gerenciar Status</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
</head>
<body>

<div class="header">
    <h2>Configurações de Status</h2>
</div>

<div class="form-container" style="max-width: 1000px; display: flex; gap: 2rem; align-items: flex-start;">

    <div style="flex: 1;">
        <h3 style="margin-bottom: 1rem; color: #333;">Status Existentes</h3>
        <table style="width: 100%;">
            <thead>
            <tr>
                <th>ID</th>
                <th>Nome</th>
                <th>Tipo</th>
            </tr>
            </thead>
            <tbody>
            <c:choose>
                <c:when test="${empty status}">
                    <tr>
                        <td colspan="3" class="empty">Nenhum status cadastrado.</td>
                    </tr>
                </c:when>
                <c:otherwise>
                    <c:forEach var="s" items="${status}">
                        <tr>
                            <td>#${s.id}</td>
                            <td><strong>${s.name}</strong></td>
                            <td>
                                <c:choose>
                                    <c:when test="${s.isFinal}">
                                        <span class="status-badge status-concluido">Finalizador</span>
                                    </c:when>
                                    <c:otherwise>
                                        <span class="status-badge status-progresso">Intermediário</span>
                                    </c:otherwise>
                                </c:choose>
                            </td>
                        </tr>
                    </c:forEach>
                </c:otherwise>
            </c:choose>
            </tbody>
        </table>
    </div>

    <div style="flex: 0 0 350px; background: #f8f9fa; padding: 1.5rem; border-radius: 8px; border: 1px solid #e9ecef;">
        <h3 style="margin-bottom: 1rem; color: #333;">Novo Status</h3>

        <form action="/status" method="POST">
            <div class="form-group">
                <label for="name">Nome do Status</label>
                <input type="text" id="name" name="name" placeholder="Ex: Em Análise" required>
            </div>

            <div class="form-group" style="display: flex; align-items: center; gap: 0.5rem; margin-top: 1.5rem;">
                <input type="hidden" name="_isFinal" value="on"/>
                <input type="checkbox" id="isFinal" name="isFinal" value="true" style="width: auto; margin: 0;">
                <label for="isFinal" style="margin: 0; cursor: pointer; font-weight: bold;">Status Final?</label>
            </div>
            <small style="color: #666; display: block; margin-top: 0.5rem; margin-bottom: 1.5rem;">
                Marque se este status encerra o ciclo de vida do chamado.
            </small>

            <button type="submit" class="btn" style="width: 100%;">Salvar Status</button>
        </form>
    </div>

</div>

<div style="margin-top: 2rem; margin-left: auto; margin-right: auto; max-width: 1000px;">
    <a href="/calls" class="btn-voltar">&larr; Voltar para Chamados</a>
</div>

</body>
</html>