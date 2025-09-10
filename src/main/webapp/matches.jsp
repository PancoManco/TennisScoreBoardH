<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Матчи</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/matches.css">
    <style><%@ include file="/css/matches.css"%></style>
</head>
<body>

<h2>Список матчей</h2>


<form class="filter-form" method="get" action="matches">
    <input type="text" name="filter_by_player_name" placeholder="Фильтр по имени игрока" value="${filterName != null ? filterName : ''}" />
    <button type="submit">Фильтровать</button>
</form>


<table>
    <thead>
    <tr>
        <th>Игрок 1</th>
        <th>Игрок 2</th>
        <th>Победитель</th>
    </tr>
    </thead>
    <tbody>
    <c:choose>
        <c:when test="${not empty matches}">
            <c:forEach var="match" items="${matches}">
                <tr>
                    <td>${match.player1.name}</td>
                    <td>${match.player2.name}</td>
                    <td><c:choose>
                        <c:when test="${not empty match.winner}">
                            ${match.winner.name}
                        </c:when>
                        <c:otherwise>
                            —
                        </c:otherwise>
                    </c:choose>
                    </td>
                </tr>
            </c:forEach>
        </c:when>
        <c:otherwise>
            <tr>
                <td colspan="3">Матчи не найдены</td>
            </tr>
        </c:otherwise>
    </c:choose>
    </tbody>
</table>

<c:set var="maxSideLinks" value="2" />
<c:set var="totalPages" value="${totalPages}" />
<c:set var="currentPage" value="${pageNumber}" />

<c:set var="startPage" value="${currentPage - maxSideLinks}" />
<c:set var="endPage" value="${currentPage + maxSideLinks}" />


<c:if test="${startPage < 1}">
    <c:set var="endPage" value="${endPage + (1 - startPage)}" />
    <c:set var="startPage" value="1" />
</c:if>


<c:if test="${endPage > totalPages}">
    <c:set var="startPage" value="${startPage - (endPage - totalPages)}" />
    <c:set var="endPage" value="${totalPages}" />
</c:if>


<c:if test="${startPage < 1}">
    <c:set var="startPage" value="1" />
</c:if>

<div class="pagination" style="margin-top: 20px;">
    <c:if test="${currentPage > 1}">
        <a href="matches?page=${currentPage - 1}&filter_by_player_name=${filterName}">← Назад</a>
    </c:if>

    <c:forEach var="i" begin="${startPage}" end="${endPage}">
        <c:choose>
            <c:when test="${i == currentPage}">
                <strong>${i}</strong>
            </c:when>
            <c:otherwise>
                <a href="matches?page=${i}&filter_by_player_name=${filterName}">${i}</a>
            </c:otherwise>
        </c:choose>
    </c:forEach>

    <c:if test="${currentPage < totalPages}">
        <a href="matches?page=${currentPage + 1}&filter_by_player_name=${filterName}">Вперёд →</a>
    </c:if>


    <form action="matches" method="get" style="display: inline-block; margin-left: 15px;">
        <input
                type="number"
                name="page"
                min="1"
                max="${totalPages}"
                value="${currentPage}"
                style="width: 50px; padding: 3px;"
                required
        />
        <input
                type="hidden"
                name="filter_by_player_name"
                value="${filterName != null ? filterName : ''}"
        />
        <button type="submit" style="padding: 4px 8px;">Перейти</button>
    </form>
</div>
<div style="text-align: center; margin-top: 20px;">
    <a href="${pageContext.request.contextPath}/" class="btn-mainpage">
        На главную
    </a>
</div>

</body>
</html>
