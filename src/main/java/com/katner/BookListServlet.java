package com.katner;

import com.katner.model.BookEntity;
import com.katner.util.EntityManagerHelper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by michal on 02.11.15.
 */
@WebServlet(name = "BookListServlet")
public class BookListServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        List<BookEntity> books;
        String authorIdString = request.getParameter("authorId");
        String tagIdString = request.getParameter("tagId");
        if (!authorIdString.isEmpty()) {
            Session session = entityManager.unwrap(Session.class);
            Criteria criteria = session.createCriteria(BookEntity.class, "book").createAlias("authors", "a");
            books = criteria.add(Restrictions.eq("a.id", Integer.parseInt(authorIdString))).list();

        } else if (!tagIdString.isEmpty()) {
            Session session = entityManager.unwrap(Session.class);
            Criteria criteria = session.createCriteria(BookEntity.class).createAlias("tags", "t");
            books = criteria.add(Restrictions.eq("t.id", Integer.parseInt(tagIdString))).list();
        } else {
            books = entityManager.createQuery("from BookEntity").getResultList();
        }
        request.setAttribute("books", books);
    }
}
