<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="ru">
<head>
    <meta charset="UTF-8">
    <title>Табло теннисного матча</title>
    <link rel="stylesheet" href="${pageContext.request.contextPath}/css/style.css">
</head>
<body>
<div class="container">
    <h1>Табло теннисного матча</h1>
    <nav>
        <a href="${pageContext.request.contextPath}/new-match">Новый матч</a>
        <a href="${pageContext.request.contextPath}/matches">Завершённые матчи</a>
    </nav>
</div>
</body>
</html>
