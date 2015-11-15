<%@ page import="com.katner.model.BookCopyEntity" %>
<%@ page import="com.katner.model.BookEntity" %>
<%@ page import="com.katner.model.RentalEntity" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<!DOCTYPE html>
<html lang="pl">
<head>
    <meta charset="utf-8">
    <meta http-equiv="X-UA-Compatible" content="IE=edge">
    <meta name="viewport" content="width=device-width, initial-scale=1">
    <!-- The above 3 meta tags *must* come first in the head; any other head content must come *after* these tags -->
    <title>Książki</title>

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
    <%@include file="WEB-INF/jspf/navigation-bar.jspf" %>
    <%@include file="WEB-INF/jspf/admin-messages.jspf" %>
    <div class="container">
        <%--<c:if test="${user.isSuperuser eq 1}">--%>
        <div class="jumbotron">
            <h2>Książki</h2>
            <c:choose>
                <c:when test="${param.q ne null}">
                    <jsp:include page="/searchServlet"/>
                </c:when>
                <c:otherwise>
                    <jsp:include page="/listBooks">
                        <jsp:param name="authorId" value="${param.authorId}"/>
                        <jsp:param name="tagId" value="${param.tagId}"/>
                    </jsp:include>
                </c:otherwise>
            </c:choose>
            <table class="table table-striped">
                <thead>
                <tr>
                    <th>Tytuł</th>
                    <th>Autorzy</th>
                    <th>Tagi</th>
                    <th>Stan magazynowy</th>
                </tr>
                </thead>
                <tbody>
                <c:if test="${not empty books}">

                    <c:forEach items="${books}" var="book">
                        <tr>
                            <td>${book.getTitle()}</td>
                            <td>
                                    <c:set var="authors" value="${book.getAuthors()}"/>
                                <c:forEach items="${authors}" var="author">
                                <a href="<c:url value="list.jsp?authorId=${author.getId()}"/>">${author.getName()}</a>
                                </c:forEach>
                            <td>
                                <c:set var="tags" value="${book.getTags()}"></c:set>
                                <c:forEach items="${tags}" var="tag">
                                    <a href="<c:url value="list.jsp?tagId=${tag.getId()}"/>"><span
                                            class="label label-primary">${tag.getTitle()}</span></a>
                                </c:forEach>
                            </td>
                            <td>
                                <%
                                    BookEntity book = (BookEntity) pageContext.getAttribute("book");
                                    Integer numOfCopies = book.getCopies().size();
                                    Integer numOfFreeCopies = numOfCopies;
                                    for (BookCopyEntity bookCopy : book.getCopies()) {
                                        for (RentalEntity rental : bookCopy.getRentals()) {
                                            if (rental.getReturnDate() == null) {
                                                numOfFreeCopies--;
                                            }
                                        }
                                    }
                                %>
                                <%=numOfFreeCopies%>/<%= numOfCopies %>
                            </td>
                            <td>
                                <c:if test="${user ne null}">
                                    <a href="rentBook.jsp?bookId=${book.getId()}"><span
                                            class="label label-info">Wypożycz</span></a>
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
