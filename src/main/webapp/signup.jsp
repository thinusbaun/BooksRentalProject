<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="en">
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
<body data-signupsuccessfull="${signupsuccessfull}">
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
                    <li><a href="http://localhost:8080/signup"><span class="glyphicon glyphicon-user"></span>
                        Rejestracja</a>
                    </li>
                    <li><a href="http://localhost:8080/login"><span class="glyphicon glyphicon-log-in"></span> Logowanie</a>
                    </li>
                </ul>
            </div>

        </div>

    </nav>
    <div class="container">
        <div class="jumbotron">
            <form id="signupform" method="post" class="form-horizontal">
                <div class="form-group has-feedback">
                    <label for="imieInput" class="control-label col-md-2">Imię</label>

                    <div class="col-md-6">
                        <input type="text" name="imie" id="imieInput" class="form-control" placeholder="Imię"/>
                        <span class="glyphicon form-control-feedback" id="fname1"></span>
                        <span class="hidden" id="imieEmpty">Imię nie może być puste.</span>
                        <span class="hidden" id="imieTooLong">Imię zbyt długie.</span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="nazwiskoInput" class="control-label col-md-2">Nazwisko</label>

                    <div class="col-md-6">
                        <input type="text" name="nazwisko" id="nazwiskoInput" class="form-control"
                               placeholder="Nazwisko"/>
                        <span class="glyphicon form-control-feedback" id="nazwisko"></span>
                        <span class="hidden" id="nazwiskoEmpty">Nazwisko nie może być puste.</span>
                        <span class="hidden" id="nazwiskoTooLong">Nazwisko zbyt długie.</span>
                    </div>
                </div>
                <div class="form-group has-feedback" id="logindiv">
                    <label for="loginInput" class="control-label col-md-2">Login</label>

                    <div class="col-md-6">
                        <input type="text" name="login" id="loginInput" class="form-control" placeholder="Login"/>
                        <span class="glyphicon form-control-feedback" id="login"></span>

                        <div class="hidden" id="loginexists">Login już istnieje!</div>
                        <span class="hidden" id="loginEmpty">Login nie może być pusty.</span>
                        <span class="hidden" id="loginTooLong">Login zbyt długi.</span>
                    </div>
                </div>
                <div class="form-group has-feedback">
                    <label for="hasloInput" class="control-label col-md-2">Hasło</label>

                    <div class="col-md-6">
                        <input type="password" name="haslo" id="hasloInput" class="form-control" placeholder="Hasło"/>
                        <span class="glyphicon form-control-feedback" id="haslo"></span>
                        <span class="hidden" id="hasloEmpty">Hasło nie może być puste.</span>
                        <span class="hidden" id="hasloTooLong">Hasło zbyt długie.</span>
                    </div>
                </div>

                <div class="form-group has-feedback">
                    <label for="emailInput" class="control-label col-md-2">Email</label>

                    <div class="col-md-6">
                        <input type="text" name="email" id="emailInput" class="form-control" placeholder="Email"/>
                        <span class="glyphicon form-control-feedback" id="email"></span>
                        <span class="hidden" id="emailEmpty">Email nie może być pusty.</span>
                        <span class="hidden" id="emailTooLong">Email zbyt długi.</span>
                        <span class="hidden" id="emailDoesNotMatch">Email nie jest prawidłowy.</span>
                    </div>
                </div>

                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary pull-right">Rejestracja</button>
                </div>
            </form>

        </div>
    </div>
</div>
<script src="js/signup.js"></script>
</body>
</html>
