package com.katner;

import com.katner.model.BookEntity;
import com.katner.util.EntityManagerHelper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Order;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by michal on 13.11.15.
 */
@WebServlet(name = "NewBooksServlet")
public class NewBooksServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        List<BookEntity> books = null;

        Session session = entityManager.unwrap(Session.class);
        Criteria criteria = session.createCriteria(BookEntity.class).addOrder(Order.desc("addedDate"));
        books = criteria.setMaxResults(10).list();

        request.setAttribute("newBooks", books);
    }
}
