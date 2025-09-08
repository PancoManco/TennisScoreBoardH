<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Завершённые матчи</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Завершённые матчи</h1>
    <form method="get" action="${pageContext.request.contextPath}/matches">
        <div class="form-group">
            <label for="filter">Поиск по имени игрока:</label>
            <input type="text" id="filter" name="filter_by_player_name" value="${param.filter_by_player_name}">
            <button type="submit">Искать</button>
        </div>
    </form>

    <c:choose>
        <c:when test="${empty matches}">
            <p>Матчи не найдены.</p>
        </c:when>
        <c:otherwise>
            <ul class="matches-list">
                <c:forEach var="match" items="${matches}">
                    <li>
                            ${match.getPlayer1().getName()} vs ${match.getPlayer2().getName()} —
                        Победитель: ${match.getWinner().getName()}
                    </li>
                </c:forEach>
            </ul>

            <!-- Пагинация -->
            <div class="pagination">
                <c:if test="${currentPage > 1}">
                    <a href="?page=${currentPage - 1}&filter_by_player_name=${param.filter_by_player_name}">← Назад</a>
                </c:if>
                <span>Страница ${currentPage}</span>
                <c:if test="${not lastPage}">
                    <a href="?page=${currentPage + 1}&filter_by_player_name=${param.filter_by_player_name}">Вперёд →</a>
                </c:if>
            </div>
        </c:otherwise>
    </c:choose>

    <a href="${pageContext.request.contextPath}/">← На главную</a>
</div>
</body>
</html>