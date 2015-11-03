package com.katner;

import com.katner.model.BookEntity;
import com.katner.model.TagEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by michal on 02.11.15.
 */
@WebServlet(name = "BookListServlet")
public class BookListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        PrintWriter out = response.getWriter();
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();
        List<BookEntity> books = entityManager.createQuery("from BookEntity").getResultList();
        out.write("<ul class=\"list-group\">");
        for (BookEntity book : books) {
            out.write("<li class=\"list-group-item\">");
            out.write(book.getTitle());
            out.write("<br/>");
            out.write(book.getIsbn());

            List<TagEntity> tags = entityManager.createQuery("select t from BookTagsEntity bt, TagEntity t where t.id = bt.tagId and bt.bookId = :bookId").setParameter("bookId", book.getId()).getResultList();
            for (TagEntity tag : tags) {
                out.write(tag.getTitle());
                out.write("<br/>");
            }
            out.write("</li>");
        }
        out.write("</ul>");
    }
}
