<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Счёт матча</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Счёт матча</h1>
    <table class="score-table">
        <tr>
            <th>Игрок</th>
            <th>Очки</th>
            <th>Геймы</th>
            <th>Сеты</th>
        </tr>
        <tr>
            <td>${match.player1.name}</td>
            <td>${match.points[0] == 41 ? 'Больше' : match.points[0]}</td>
            <td>${match.games[0]}</td>
            <td>${match.sets[0]}</td>
        </tr>
        <tr>
            <td>${match.player2.name}</td>
            <td>${match.points[1] == 41 ? 'Больше' : match.points[1]}</td>
            <td>${match.games[1]}</td>
            <td>${match.sets[1]}</td>
        </tr>
    </table>

    <c:if test="${match.tieBreak}">
        <p><strong>Тай-брейк!</strong></p>
    </c:if>

    <form action="${pageContext.request.contextPath}/match-score" method="post">
        <input type="hidden" name="uuid" value="${param.uuid}">
        <button type="submit" name="winnerId" value="1">Игрок 1 выиграл очко</button>
        <button type="submit" name="winnerId" value="2">Игрок 2 выиграл очко</button>
    </form>

    <a href="${pageContext.request.contextPath}/">← На главную</a>
</div>
</body>
</html>