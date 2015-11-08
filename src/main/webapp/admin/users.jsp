<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Użytkownicy</title>

    <!-- Bootstrap -->
    <link href="../css/bootstrap.min.css" rel="stylesheet">
    <link href="../css/books.css" rel="stylesheet">
    <script src="../js/jquery.min.js"></script>
    <script src="../js/bootstrap.min.js"></script>

    <!-- HTML5 shim and Respond.js for IE8 support of HTML5 elements and media queries -->
    <!-- WARNING: Respond.js doesn't work if you view the page via file:// -->
    <!--[if lt IE 9]>
    <script src="https://oss.maxcdn.com/html5shiv/3.7.2/html5shiv.min.js"></script>
    <script src="https://oss.maxcdn.com/respond/1.4.2/respond.min.js"></script>
    <![endif]-->

    <script type="text/javascript">
        $(document).ready(function () {
            $("input[id*='staff").each(function (i, el) {
                $(el).change(function (event) {
                    var id = event.target.id.split("-")[0];
                    $.post("/listUsers", {"userId": id, "setIsStaff": event.target.checked});
                })
            })
            $("input[id*='superuser").each(function (i, el) {
                $(el).change(function (event) {
                    var id = event.target.id.split("-")[0];
                    $.post("/listUsers", {"userId": id, "setIsSuperuser": event.target.checked});
                })
            })
            $("input[id*='active").each(function (i, el) {
                $(el).change(function (event) {
                    var id = event.target.id.split("-")[0];
                    $.post("/listUsers", {"userId": id, "setIsActive": event.target.checked});
                })
            })
            $("a[id*='delete").each(function (i, el) {
                $(el).click(function (event) {
                    var id = event.target.id.split("-")[0];
                    $.post("/listUsers", {"userId": id, "deleteUser": "true"});
                })
            })
        })
    </script>

</head>
<body>
<div class="container">
    <%@include file="../WEB-INF/jspf/navigation-bar.jspf" %>
    <%@include file="../WEB-INF/jspf/admin-messages.jspf" %>
    <div class="container">
        <%--<c:if test="${user.isSuperuser eq 1}">--%>
        <div class="jumbotron">
            <h2>Użytkownicy</h2>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Login</th>
                    <th>Imię</th>
                    <th>Nazwisko</th>
                    <th>Email</th>
                    <th>Pracownik</th>
                    <th>Administrator</th>
                    <th>Zatwierdzony</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/listUsers"></jsp:include>
                <c:if test="${not empty users}">
                    <c:forEach items="${users}" var="user">
                        <tr id="${user.getId()}-row">
                            <td>${user.getUsername()}</td>
                            <td>${user.getFirstName()}</td>
                            <td>${user.getLastName()}</td>
                            <td>${user.getEmail()}</td>
                            <td><input type="checkbox" id="${user.getId()}-staff"
                                       <c:if test="${user.getIsStaff() eq 1}">checked="checked"</c:if>></td>
                            <td><input type="checkbox" id="${user.getId()}-superuser"
                                       <c:if test="${user.getIsSuperuser() eq 1}">checked="checked"</c:if>></td>
                            <td><input type="checkbox" id="${user.getId()}-active"
                                       <c:if test="${user.getIsActive() eq 1}">checked="checked"</c:if>></td>
                            <td><a class="deleteUser btn btn-primary pull-right" id="${user.getId()}-delete">Usuń</a>
                            </td>
                        </tr>
                    </c:forEach>
                </c:if>

                </tbody>
            </table>
        </div>
        <%--</c:if>--%>
    </div>
</div>
</body>
</html>
