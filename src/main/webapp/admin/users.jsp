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

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>

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
                    <c:if test="${user.getIsSuperuser() eq 1}">
                    <th>Pracownik</th>
                    <th>Administrator</th>
                    </c:if>
                    <th>Zatwierdzony</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/listUsers"></jsp:include>
                <c:if test="${not empty users}">
                    <c:forEach items="${users}" var="userEntry">
                        <tr id="${userEntry.getId()}-row">
                            <td>${userEntry.getUsername()}</td>
                            <td>${userEntry.getFirstName()}</td>
                            <td>${userEntry.getLastName()}</td>
                            <td>${userEntry.getEmail()}</td>
                            <c:if test="${user.getIsSuperuser() eq 1}">
                                <td><input type="checkbox" id="${userEntry.getId()}-staff"
                                           <c:if test="${userEntry.getIsStaff() eq 1}">checked="checked"</c:if>></td>
                                <td><input type="checkbox" id="${userEntry.getId()}-superuser"
                                           <c:if test="${userEntry.getIsSuperuser() eq 1}">checked="checked"</c:if>>
                                </td>
                            </c:if>
                            <td><input type="checkbox" id="${userEntry.getId()}-active"
                                       <c:if test="${userEntry.getIsActive() eq 1}">checked="checked"</c:if>></td>
                            <td><a class="deleteUser btn btn-primary pull-right"
                                   id="${userEntry.getId()}-delete">Usuń</a>
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
