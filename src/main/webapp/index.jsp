<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Wypożyczalnia książek</title>

    <!-- Bootstrap -->
    <link href="css/bootstrap.min.css" rel="stylesheet">
    <link href="css/books.css" rel="stylesheet">
    <link rel="stylesheet" href="css/jquery-ui.css">
    <script src="js/jquery.min.js"></script>
    <script src="js/jquery-ui.js"></script>
    <script src="js/bootstrap.min.js"></script>
    <script src="js/js.cookie.js"></script>
    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/jspf/navigation-bar.jspf" %>
    <%@include file="WEB-INF/jspf/admin-messages.jspf" %>
    <%@include file="WEB-INF/jspf/new-books.jspf" %>
    <div class="container">
        <c:if test="${empty user}">
        <div class="jumbotron">
            <h1>Wypożyczalnia książek</h1>
            <p>To jest projekt na przedmiot Programowanie Aplikacji WWW na Politechnice Białostockiej.</p>
            <p><a class="btn btn-primary btn-lg" href="http://localhost:8080/signup" role="button">Zarejestruj się!</a></p>
        </div>
        </c:if>
    </div>
</div>
</body>
</html>