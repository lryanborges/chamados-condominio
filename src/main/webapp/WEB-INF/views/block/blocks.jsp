<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>

<!DOCTYPE html>
<html>
<head>
    <meta charset="UTF-8">
    <title>Blocos</title>
    <link rel="stylesheet" type="text/css" href="/css/blocks.css" />
</head>
<body>
<div class="header">
    <h2>Blocos</h2>
    <a href="/blocks/new" class="btn">+ Novo Bloco</a>
</div>

<table>
    <thead>
    <tr>
        <th>Identificação</th>
        <th>Andares</th>
        <th>Unidades por Andar</th>
        <th>Total de Unidades</th>
        <th>Gerenciar Unidades</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${empty blocks}">
            <tr><td colspan="4" class="empty">Nenhum bloco cadastrado.</td></tr>
        </c:when>
        <c:otherwise>
            <c:forEach var="block" items="${blocks}">
                <tr>
                    <td>${block.identity}</td>
                    <td>${block.qtdFloors}</td>
                    <td>${block.nUnitsPerFloor}</td>
                    <td>${block.qtdFloors * block.nUnitsPerFloor}</td>
                    <td>
                        <a href="/units/${block.id}" class="btn-detalhes">
                            Ver Unidades
                        </a>
                    </td>
                </tr>
            </c:forEach>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<div style="margin-top: 1rem;">
    <a href="/home" class="btn-voltar">
        Voltar
    </a>
</div>
</body>
</html>