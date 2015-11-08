package com.katner;

import com.katner.model.BookEntity;
import com.katner.util.EntityManagerHelper;

import javax.persistence.EntityManager;
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
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        List<BookEntity> books = entityManager.createQuery("from BookEntity").getResultList();
        request.setAttribute("books", books);
    }
}
