<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
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
        <thead>
        <tr>
            <th>Игрок</th>
            <th>Сеты</th>
            <th>Геймы</th>
            <th>Очки</th>
        </tr>
        </thead>
        <tbody>
        <c:set var="currentSet" value="${matchScore.getCurrentSet()}"/>
        <tr>
            <td>${matchScore.getPlayer1().getName()}</td>
            <td>${matchScore.getSetsWonPlayer1()}</td>
            <td>${matchScore.getCurrentSet().getGames(matchScore.getPlayer1())}</td>
            <td>${matchScore.getCurrentSet().getCurrentGameScore(matchScore.getPlayer1())}</td>
        </tr>
        <tr>
            <td>${matchScore.getPlayer2().getName()}</td>
            <td>${matchScore.getSetsWonPlayer2()}</td>
            <td>${matchScore.getCurrentSet().getGames(matchScore.getPlayer2())}</td>
            <td>${matchScore.getCurrentSet().getCurrentGameScore(matchScore.getPlayer2())}</td>
        </tr>
        </tbody>
    </table>

    <c:if test="${matchScore.isMatchOver()}">
        <p><strong>Матч завершен!</strong></p>
    </c:if>

    <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="post">
        <input type="hidden" name="uuid" value="${param.uuid}">
        <button type="submit" name="winnerId" value=${matchScore.getPlayer1().getName()}>Игрок 1 выиграл очко</button>
        <button type="submit" name="winnerId" value=${matchScore.getPlayer2().getName()}>Игрок 2 выиграл очко</button>
    </form>

    <a href="${pageContext.request.contextPath}/">← На главную</a>
</div>
</body>
</html>