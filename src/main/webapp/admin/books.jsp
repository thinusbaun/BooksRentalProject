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
    <title>Administracja książkami</title>

    <%@ include file="/WEB-INF/jspf/imports.jspf" %>
    <script type="text/javascript">
        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "/authorManage",
                data: {'json': 1},
                success: function (data) {
                    var opts = $.parseJSON(data);
                    $.each(opts, function (i, d) {
                        $('#authorSelect').append('<option value="' + d.id + '">' + d.name + '</option>');
                    });
                }
            });
        })

        $(document).ready(function () {
            $.ajax({
                type: "GET",
                url: "/tagManage",
                data: {'json': 1},
                success: function (data) {
                    var opts = $.parseJSON(data);
                    $.each(opts, function (i, d) {
                        $('#tagSelect').append('<option value="' + d.id + '">' + d.title + '</option>');
                    });
                }
            });
        })

        function showAddAuthorDialog(ob) {
            var bookIdInput = $("#bookIdToAuthorAdd")[0];
            bookIdInput.setAttribute('value', ob.getAttribute('bookId'));
            $('#addAuthorModal').modal('show');
        }

        function showAddTagDialog(ob) {
            var bookIdInput = $("#bookIdToTagAdd")[0];
            bookIdInput.setAttribute('value', ob.getAttribute('bookId'));
            $('#addTagModal').modal('show');
        }

        $(function () {
            $("button#submit").click(function (event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/bookManage",
                    data: $('form#newBookForm').serialize()
                }).success(function () {
                    location.reload();
                });

            });
        });

        $(function () {
            $("button#submitAuthor").click(function (event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/bookManage",
                    data: $('form#addAuthorForm').serialize()
                }).success(function () {
                    location.reload();
                });

            });
        });

        $(function () {
            $("button#submitTag").click(function (event) {
                event.preventDefault();
                $.ajax({
                    type: "POST",
                    url: "/bookManage",
                    data: $('form#addTagForm').serialize()
                }).success(function () {
                    location.reload();
                });

            });
        });

        function removeBook(button) {
            console.log(button.getAttribute('bookid'));
            $.ajax({
                type: "POST",
                url: "/bookManage",
                data: {removeBookId: button.getAttribute('bookid')},
                success: function () {
                    $("tr[bookid='" + button.getAttribute('bookid') + "']").remove();
                }
            });
        }
        function removeEmptyBooks() {
            $.ajax({
                type: "POST",
                url: "/bookManage",
                data: {removeEmptyBooks: 1},
                success: function () {
                    location.reload();
                }
            });
        }
        function removeTagFromBook(button) {
            console.log(button.getAttribute('tagid'));
            console.log(button.getAttribute('bookid'));
            $.ajax({
                type: "POST",
                url: "/bookManage",
                data: {tagToRemoveId: button.getAttribute('tagid'), tagToRemoveBookId: button.getAttribute('bookid')},
                success: function () {
                    button.remove();
                    $("span#tag-" + button.getAttribute('bookid') + "-" + button.getAttribute('tagid')).empty();
                }
            });
        }
        function removeAuthorFromBook(button) {
            console.log(button.getAttribute('authorid'));
            console.log(button.getAttribute('bookid'));
            $.ajax({
                type: "POST",
                url: "/bookManage",
                data: {
                    authorToRemoveId: button.getAttribute('author'),
                    authorToRemoveBookId: button.getAttribute('bookid')
                },
                success: function () {
                    button.remove();
                    $("span#author-" + button.getAttribute('bookid') + "-" + button.getAttribute('authorid')).empty();
                }
            });
        }
    </script>

</head>
<body>
<div id="addBookModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dodaj książkę</h4>
            </div>
            <form id="newBookForm" method="post">
                <div class="modal-body">
                    <input type="text" class="hidden" name="shouldAddBook" value="1"/>
                    <label for="bookTitle">Tytuł książki:</label> <input type="text" name="bookTitle"
                                                                         id="bookTitle"/><br/>
                    <label for="bookIsbn">Numer ISBN:</label> <input type="text" name="bookIsbn" id="bookIsbn"/><br/>
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
        <%--<c:if test="${user.isSuperuser eq 1}">--%>
        <div class="jumbotron">
            <h2>Książki</h2>
            <button type="button" class="btn btn-default" data-toggle="modal" data-target="#addBookModal">
                Nowa książka
            </button>
            <button type="button" class="btn btn-default" onclick="removeEmptyBooks()">
                Usuń książki bez autorów
            </button>
            <jsp:include page="/listBooks">
                <jsp:param name="authorId" value="${param.authorId}"/>
                <jsp:param name="tagId" value="${param.tagId}"/>
            </jsp:include>
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
                        <tr bookId="${book.getId()}">
                            <td>${book.getTitle()}</td>
                            <td>
                                    <c:set var="authors" value="${book.getAuthors()}"/>
                                <c:forEach items="${authors}" var="author">
                                <span id="author-${book.getId()}-${author.getId()}"><a
                                        href="<c:url value="list.jsp?authorId=${author.getId()}"/>">${author.getName()}</a><span
                                        bookId="${book.getId()}" authorId="${author.getId()}"
                                        onclick="removeAuthorFromBook(this)">&times;</span></span>
                                </c:forEach>
                                        <span class="label label-success" onclick="showAddAuthorDialog(this)"
                                              bookId="${book.getId()}">+</span>
                            <td>
                                <c:set var="tags" value="${book.getTags()}"></c:set>
                                <c:forEach items="${tags}" var="tag">

                                  <span id="tag-${book.getId()}-${tag.getId()}">  <a
                                          href="<c:url value="list.jsp?tagId=${tag.getId()}"/>"><span
                                          class="label label-primary">${tag.getTitle()}</a></span> <span
                                        bookId="${book.getId()}" tagId="${tag.getId()}"
                                        onclick="removeTagFromBook(this)">&times;</span></span>
                                </c:forEach>
                                <span class="label label-success" onclick="showAddTagDialog(this)"
                                      bookId="${book.getId()}">+</span>
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
                                <button type="button" class="btn btn-default" bookId="${book.getId()}"
                                        onclick="removeBook(this)">Usuń
                                </button>
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

<div id="addAuthorModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dodaj autora</h4>
            </div>
            <form id="addAuthorForm" method="post">
                <input type="text" class="hidden" name="bookId" id="bookIdToAuthorAdd"/>
                <select id="authorSelect" name="addAuthorId"></select>

                <div class="modal-body">
                    <button class="btn btn-success btn-xs" id="submitAuthor">Dodaj</button>

                </div>
            </form>
        </div>
    </div>
</div>

<div id="addTagModal" class="modal fade" role="dialog">
    <div class="modal-dialog">

        <!-- Modal content-->
        <div class="modal-content">
            <div class="modal-header">
                <button type="button" class="close" data-dismiss="modal">&times;</button>
                <h4 class="modal-title">Dodaj tag</h4>
            </div>
            <form id="addTagForm" method="post">
                <input type="text" class="hidden" name="bookId" id="bookIdToTagAdd"/>
                <select id="tagSelect" name="addTagId"></select>

                <div class="modal-body">
                    <button class="btn btn-success btn-xs" id="submitTag">Dodaj</button>

                </div>
            </form>
        </div>
    </div>
</div>
</body>
</html>
