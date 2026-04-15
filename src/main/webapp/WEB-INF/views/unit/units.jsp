<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Unidades do Bloco</title>
    <link rel="stylesheet" type="text/css" href="/css/blocks.css">
</head>
<body>
<div class="header">
    <h2>Unidades do Bloco: ${blockId}</h2>
</div>

<table>
    <thead>
    <tr>
        <th>ID</th>
        <th>Andar</th>
        <th>Identificador (Apto)</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty units}">
            <tr>
                <td colspan="4" class="empty">Nenhuma unidade encontrada para este bloco.</td>
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