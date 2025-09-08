<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Табло теннисного матча</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/index.css">
    <style><%@ include file="/css/index.css"%></style>
</head>
<body>
<div class="container">
    <header>
        <img src="/images/tennis-ball.png" alt="Теннисный мяч" class="logo-ball">
        <h1>Табло теннисного матча</h1>
        <img src="/images/tennis-racket.png" alt="Ракетка" class="logo-racket">
    </header>
    <nav>
        <a href="${pageContext.request.contextPath}/new-match" class="btn">Новый матч</a>
        <a href="${pageContext.request.contextPath}/matches" class="btn">Завершённые матчи</a>
    </nav>
</div>
</body>
</html>
