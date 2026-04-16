<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unidades do Bloco</title>
    <link rel="stylesheet" type="text/css" href="/css/styles.css">
    <style>
        .user-tag {
            display: inline-block;
            background: #e3f2fd;
            color: #1976d2;
            padding: 2px 8px;
            border-radius: 12px;
            font-size: 0.8rem;
            margin-bottom: 4px;
            border: 1px solid #bbdefb;
        }
        .form-vincular {
            display: flex;
            gap: 5px;
            align-items: center;
        }
        select {
            padding: 4px;
            border-radius: 4px;
            border: 1px solid #ccc;
        }
    </style>
</head>
<body>
<div class="header">
    <h2>Unidades do Bloco ${block.identity}</h2>
</div>

<c:if test="${not empty errorMessage}">
    <div style="background-color: #f8d7da; color: #721c24; padding: 10px; border-radius: 4px; margin-bottom: 20px; border: 1px solid #f5c6cb;">
            ${errorMessage}
    </div>
</c:if>

<c:if test="${not empty successMessage}">
    <div style="background-color: #d4edda; color: #155724; padding: 10px; border-radius: 4px; margin-bottom: 20px; border: 1px solid #c3e6cb;">
            ${successMessage}
    </div>
</c:if>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Andar</th>
        <th>Identificador (Apto)</th>
        <th>Moradores Atuais</th>
        <th>Vincular Novo Morador</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty units}">
            <tr>
                <td colspan="5" class="empty">Nenhuma unidade encontrada para este bloco.</td>
            </tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="unit" items="${units}">
                <tr>
                    <td>${unit.id}</td>
                    <td>
                        <c:choose>
                            <c:when test="${unit.floor == 0}">Térreo</c:when>
                            <c:otherwise>${unit.floor}º Andar</c:otherwise>
                        </c:choose>
                    </td>
                    <td><strong>${unit.identifier}</strong></td>
                    <td>
                        <c:choose>
                            <c:when test="${empty unit.users}">
                                <span style="color: #999; font-style: italic;">Vago</span>
                            </c:when>
                            <c:otherwise>
                                <c:forEach var="user" items="${unit.users}">
                                    <div class="user-tag">
                                        ID: ${user.id} - ${user.name}
                                    </div>
                                </c:forEach>
                            </c:otherwise>
                        </c:choose>
                    </td>
                    <td>
                        <form action="/units/link-user" method="POST" class="form-vincular">
                            <input type="hidden" name="unitId" value="${unit.id}">
                            <input type="hidden" name="blockId" value="${block.id}">

                            <select name="userId" required>
                                <option value="">Selecionar Usuário...</option>
                                <c:forEach var="u" items="${allUsers}">
                                    <option value="${u.id}">ID: ${u.id} - ${u.name}</option>
                                </c:forEach>
                            </select>

                            <button type="submit" class="btn-save" style="padding: 5px 10px;">
                                Vincular
                            </button>
                        </form>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<div style="margin-top: 2rem;">
    <a href="/blocks" class="btn-voltar">
        Voltar
    </a>
</div>
</body>
</html>