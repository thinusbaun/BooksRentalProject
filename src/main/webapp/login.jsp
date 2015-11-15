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

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>


</head>
<body data-signupsuccessfull="${signupsuccessfull}">
<div class="container">
    <%@include file="WEB-INF/jspf/navigation-bar.jspf" %>
    <%@include file="WEB-INF/jspf/admin-messages.jspf" %>
    <div class="container">
        <div class="jumbotron">
            <c:if test="${not empty message }">
                <div class="alert alert-warning fade in">
                    <a href="#" class="close" data-dismiss="alert" aria-label="close">&times;</a>
                    <%=session.getAttribute("message")%>
                </div>
            </c:if>
            <form id="signupform" method="post" class="form-horizontal">
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

                <div class="col-md-2">
                    <button type="submit" class="btn btn-primary pull-right">Zaloguj</button>
                </div>
            </form>

        </div>
    </div>
</div>
<%--<script src="<c:url value="js/signup.js"/>"></script>--%>
</body>
</html>
