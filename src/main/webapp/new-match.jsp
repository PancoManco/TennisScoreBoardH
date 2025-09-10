<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Новый матч</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <style><%@ include file="/css/new-match.css"%></style>
</head>
<body>
<div class="container">
    <h1>Новый матч</h1>
    <form action="${pageContext.request.contextPath}/new-match" method="post">
        <div class="form-group">
            <label for="player1">Имя игрока 1:</label>
            <input type="text" id="player1" name="player1" required>
        </div>
        <div class="form-group">
            <label for="player2">Имя игрока 2:</label>
            <input type="text" id="player2" name="player2" required>
        </div>
        <button type="submit">Начать</button>
    </form>
    <c:if test="${not empty requestScope.errorMessage}">
        <div style="color: red; font-weight: bold; margin-bottom: 1em;">Ошибка: ${requestScope.errorMessage}</div>
    </c:if>
    <a href="${pageContext.request.contextPath}/">← На главную</a>
</div>

</body>
</html>