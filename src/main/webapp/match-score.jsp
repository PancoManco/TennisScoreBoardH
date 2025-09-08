<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Счёт матча</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/match-score.css">
    <style><%@ include file="/css/match-score.css"%></style>
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

    <c:if test="${requestScope.matchScore.isMatchOver() and not empty requestScope.matchScore.getWinner()}">
        <h2>Матч завершён!</h2>
        <h2>🏆 Победитель: ${requestScope.matchScore.winner}</h2>
    </c:if>

    <c:if test="${not matchScore.isMatchOver()}">
        <form action="${pageContext.request.contextPath}/match-score?uuid=${uuid}" method="post">
            <input type="hidden" name="uuid" value="${param.uuid}">
            <button type="submit" name="winnerId" value="${matchScore.getPlayer1().getName()}">
                Игрок ${matchScore.getPlayer1().getName()} выиграл очко
            </button>
            <button type="submit" name="winnerId" value="${matchScore.getPlayer2().getName()}">
                Игрок ${matchScore.getPlayer2().getName()} выиграл очко
            </button>
        </form>
    </c:if>

    <a href="${pageContext.request.contextPath}/">← На главную</a>
</div>
</body>
</html>
