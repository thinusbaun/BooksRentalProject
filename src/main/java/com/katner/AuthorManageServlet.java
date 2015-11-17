package com.katner;

import com.katner.model.AuthorEntity;
import com.katner.util.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by michal on 16.11.15.
 */
@WebServlet(name = "AuthorManageServlet")
public class AuthorManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorNameToAdd = request.getParameter("authorNameToAdd");
        if (authorNameToAdd != null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            AuthorEntity author = new AuthorEntity();
            author.setName(authorNameToAdd);
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<AuthorEntity> authors;
        authors = em.createQuery("from AuthorEntity").getResultList();
        request.setAttribute("authors", authors);
    }
}
