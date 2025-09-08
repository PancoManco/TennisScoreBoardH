<!DOCTYPE html>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html lang="ru">
<head>
  <meta charset="UTF-8">
  <title>Ошибка</title>
  <style>
    body {
      text-align: center;
      margin-top: 50px;
    }
    h1 {
      color: #FF0000;
    }
  </style>
</head>
<body>
<h1>Ой, произошла ошибка!</h1>
<p>Описание ошибки: ${requestScope.errorMessage}</p>
<a href="${pageContext.request.contextPath}/">Вернуться на главную</a>
</body>
</html>