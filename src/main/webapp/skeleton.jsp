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
    <script src="js/jquery.min.js"></script>
    <script src="js/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->
</head>
<body>
<div class="container">
    <nav class="navbar navbar-inverse navbar-fixed-top">
        <div class="container">


            <div class="navbar-header">
                <button type="button" class="navbar-toggle collapsed" data-toggle="collapse" data-target="#navbar"
                        aria-expanded="false" aria-controls="navbar">
                    <span class="sr-only">Toggle navigation</span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                    <span class="icon-bar"></span>
                </button>
                <a class="navbar-brand" href="#">Wypożyczalnia książek</a>
            </div>
            <div id="navbar" class="collapse navbar-collapse">
                <ul class="nav navbar-nav">
                    <li><a href="#books">Książki</a></li>
                </ul>
                <ul class="nav navbar-nav navbar-right">
                    <%
                        String userFirstName = null;
                        Cookie[] cookies = request.getCookies();
                        if (cookies != null) {
                            for (Cookie cookie : cookies) {
                                if (cookie.getName().equals("imie")) userFirstName = cookie.getValue();
                            }
                        }
                        if (userFirstName == null) {
                            out.write("<li><a href=\"http://localhost:8080/signup\"><span class=\"glyphicon glyphicon-user\"></span> Rejestracja</a></li>");
                            out.write("<li><a href=\"http://localhost:8080/login\"><span class=\"glyphicon glyphicon-log-in\"></span> Logowanie</a></li>");
                        } else {
                            out.write("<li><a><span class=\"glyphicon glyphicon-user\"></span> " + userFirstName + "</a></li>");
                            out.write("<li><a href=\"http://localhost:8080/logout\"><span class=\"glyphicon glyphicon-log-out\"></span> Wyloguj</a></li>");
                        }
                    %>

                </ul>
            </div>

        </div>

    </nav>