<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Komunikaty administratora</title>

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>

    <script type="text/javascript">
        $(document).ready(function () {
            $('#newMessageButton').click(function () {
                $('#newMessageDiv').toggleClass("hidden");
            })
            $('#sendNewMessage').click(function () {
                var editor = $('#editor');
                $.post("/listAdminMessages", {'newMessageContent': editor.text()}).done(function () {
                    location.reload();
                });
                editor.text("Tutaj wpisz treść komunikatu");
                $('#newMessageDiv').toggleClass("hidden");
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
            <h2>Komunikaty administratora</h2>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Treść</th>
                    <th></th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/listAdminMessages"></jsp:include>
                <c:if test="${not empty messages}">
                <c:forEach items="${messages}" var="message">
                <tr id="${message.getId()}-row">
                    <td>${message.getContent()}</td>
                    <td>
                        <a class="removeMessage btn btn-primary pull-right" id="${message.getId()}">Usuń</a>
        </div>
        </td>
        </tr>

        </c:forEach>
        </c:if>

        </tbody>
        </table>
        <a class="btn btn-primary" id="newMessageButton">Nowy komunikat</a>

        <div id="newMessageDiv" class="hidden">
            <div id="editor" contenteditable="true">Tutaj wpisz treść komunikatu</div>
            <a class="btn btn-primary" id="sendNewMessage">Wyślij</a>
        </div>

    </div>
    <%--</c:if>--%>
</div>
</div>
</body>
</html>