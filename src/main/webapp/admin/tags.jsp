<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Administracja tagami</title>

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>
    <script type="text/javascript">
        $(function () {
            $("button#submit").click(function (event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/tagManage",
                    data: $('form#newTagForm').serialize()
                });
                location.reload();
            });
        });

        function removeTag(button) {
            console.log(button.getAttribute('tagid'));
            $.ajax({
                type: "POST",
                url: "/tagManage",
                data: {removeAuthorId: button.getAttribute('tagid')},
                success: function () {
                    $("tr[tagid='" + button.getAttribute('tagid') + "']").remove();
                }
            });
        }
        function removeEmptyTags() {
            $.ajax({
                type: "POST",
                url: "/tagManage",
                data: {removeEmptyTags: 1},
                success: function () {
                    location.reload();
                }
            });
        }
    </script>

</head>
<body>
<div id="addTagModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dodaj tag</h4>
            </div>
            <form id="newTagForm" method="post">
                <div class="modal-body">
                    <input type="text" name="tagTitleToAdd" id="tagTitle"/>
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
            <h2>Tagi</h2>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addTagModal">
                Nowy tag
            </button>
            <button type="button" class="btn btn-default" onclick="removeEmptyTags()">
                Usuń tagi bez książek
            </button>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Autor</th>
                    <th>Ilość książek</th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/tagManage"/>
                <c:forEach items="${tags}" var="tag">
                    <tr tagId="${tag.getId()}">
                        <td>${tag.getTitle()}</td>
                        <td>${tag.getBooks().size()}</td>
                        <td>
                            <button type="button" class="btn btn-default" tagId="${tag.getId()}"
                                    onclick="removeTag(this)">Usuń
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
