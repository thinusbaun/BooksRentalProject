<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Wypożycz książkę</title>

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>
    <script type="text/javascript">
        $(document).ready(function () {

            $("a[class='rentBookButton").each(function (i, el) {
                $(el).click(function (event) {
                    var box = $(el).children()[0];
                    if ($(box).hasClass('label-info')) {
                        var id = el.id;
                        $.post("/bookRent", {"rentBookId": id});
                        $(box).removeClass('label-info');
                        $(box).addClass('label-default');
                    }
                })
            })
        })
    </script>
</head>
<body>
<div class="container">
    <%@include file="WEB-INF/jspf/navigation-bar.jspf" %>
    <%@include file="WEB-INF/jspf/admin-messages.jspf" %>
    <div class="container">
        <%--<c:if test="${user.isSuperuser eq 1}">--%>
        <div class="jumbotron">
            <h2>Egzemplarze książki</h2>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Tytuł</th>
                    <th>Autorzy</th>
                    <th>Tagi</th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/bookCopy">
                    <jsp:param name="bookId" value="${param.bookId}"/>
                </jsp:include>
                <c:if test="${not empty copies}">


                    <c:forEach items="${copies}" var="copy">
                        <tr>
                            <td>${copy.getBook().getTitle()}</td>
                            <td>
                                    <c:set var="authors" value="${copy.getBook().getAuthors()}"/>
                                <c:forEach items="${authors}" var="author">
                                <a href="<c:url value="list.jsp?authorId=${author.getId()}"/>">${author.getName()}</a>
                                </c:forEach>
                            <td>
                                <c:set var="tags" value="${copy.getBook().getTags()}"></c:set>
                                <c:forEach items="${tags}" var="tag">
                                    <a href="<c:url value="list.jsp?tagId=${tag.getId()}"/>"><span
                                            class="label label-primary">${tag.getTitle()}</span></a>
                                </c:forEach>
                            </td>
                            <td>
                                <a class="rentBookButton" id="${copy.getId()}"><span
                                        class="label label-info">Wypożycz</span></a>
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
