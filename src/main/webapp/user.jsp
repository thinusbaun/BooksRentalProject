<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Konto użytkownika</title>

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

    <script type="text/javascript">
        $(document).ready(function () {

            $("a[class='returnButton").each(function (i, el) {
                $(el).click(function (event) {
                    var box = $(el).children()[0];
                    if ($(box).hasClass('label-info')) {
                        var id = el.id;
                        console.log(id);
                        console.log($(el).id);
                        $.post("/bookRent", {"returnRentalId": id});
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
            <h2>Książki</h2>

            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Tytuł</th>
                    <th>Autorzy</th>
                    <th>Tagi</th>
                </tr>
                </thead>
                <tbody>
                <jsp:include page="/bookRent">
                    <jsp:param name="userId" value="${user.getId()}"/>
                </jsp:include>
                <c:if test="${not empty rentals}">

                    <c:forEach items="${rentals}" var="rental">
                        <tr>
                            <td>${rental.getBookCopy().getBook().getTitle()}</td>
                            <td>
                                    <c:set var="authors" value="${rental.getBookCopy().getBook().getAuthors()}"/>
                                <c:forEach items="${authors}" var="author">
                                <a href="<c:url value="list.jsp?authorId=${author.getId()}"/>">${author.getName()}</a>
                                </c:forEach>
                            <td>
                                <c:set var="tags" value="${book.getBookCopy().getBook().getTags()}"></c:set>
                                <c:forEach items="${tags}" var="tag">
                                    <a href="<c:url value="list.jsp?tagId=${tag.getId()}"/>"><span
                                            class="label label-primary">${tag.getTitle()}</span></a>
                                </c:forEach>
                            </td>
                            <td>
                                <c:if test="${user ne null}">
                                    <a class="returnButton" id="${rental.getId()}"><span
                                            class="label label-info">Zwróć</span></a>
                                </c:if>
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
