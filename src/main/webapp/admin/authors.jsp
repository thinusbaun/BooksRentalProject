<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Administracja autorami</title>

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>
    <script type="text/javascript">
        $(function () {
            $("button#submit").click(function (event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/authorManage",
                    data: $('form#newAuthorForm').serialize()
                });
                location.reload();
            });
        });

        function removeAuthor(button) {
            console.log(button.getAttribute('authorid'));
            $.ajax({
                type: "POST",
                url: "/authorManage",
                data: {removeAuthorId: button.getAttribute('authorid')},
                success: function () {
                    $("tr[authorid='" + button.getAttribute('authorid') + "']").remove();
                }
            });
        }
        function removeEmptyAuthors() {
            $.ajax({
                type: "POST",
                url: "/authorManage",
                data: {removeEmptyAuthors: 1},
                success: function () {
                    location.reload();
                }
            });
        }
    </script>

</head>
<body>
<div id="addAuthorModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dodaj autora</h4>
            </div>
            <form id="newAuthorForm" method="post">
                <div class="modal-body">
                    <input type="text" name="authorNameToAdd" id="authorName"/>
                    <button class="btn btn-success btn-xs" id="submit">Dodaj</button>

                </div>
            </form>
        </div>
    </div>
</div>


<div class="container">
    <%@include file="/WEB-INF/jspf/navigation-bar.jspf" %>
    <%@include file="/WEB-INF/jspf/admin-messages.jspf" %>
    <div class="container">
        <div class="jumbotron">
            <h2>Autorzy</h2>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addAuthorModal">
                Nowy autor
            </button>
            <button type="button" class="btn btn-default" onclick="removeEmptyAuthors()">
                Usuń autorów bez książek
            </button>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Autor</th>
                    <th>Ilość książek</th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/authorManage"/>
                <c:forEach items="${authors}" var="author">
                    <tr authorId="${author.getId()}">
                        <td>${author.getName()}</td>
                        <td>${author.getBooks().size()}</td>
                        <td>
                            <button type="button" class="btn btn-default" authorId="${author.getId()}"
                                    onclick="removeAuthor(this)">Usuń
                            </button>
                        </td>
                    </tr>
                </c:forEach>
                </tbody>
            </table>
        </div>
    </div>
</div>
</body>
</html>
