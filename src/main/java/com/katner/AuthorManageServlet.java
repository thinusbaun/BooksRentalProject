package com.katner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katner.model.AuthorEntity;
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
 * Created by michal on 16.11.15.
 */
@WebServlet(name = "AuthorManageServlet")
public class AuthorManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String authorNameToAdd = request.getParameter("authorNameToAdd");
        String removeAuthorId = request.getParameter("removeAuthorId");
        String removeEmptyAuthors = request.getParameter("removeEmptyAuthors");
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (authorNameToAdd != null) {
            AuthorEntity author = new AuthorEntity();
            author.setName(authorNameToAdd);
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        }
        if (removeAuthorId != null) {
            AuthorEntity author = em.find(AuthorEntity.class, Integer.parseInt(removeAuthorId));
            em.getTransaction().begin();
            javax.persistence.Query q = em.createQuery("delete BookAuthorsEntity where authorId = :authorid").setParameter("authorid", Integer.parseInt(removeAuthorId));
            q.executeUpdate();
            em.remove(author);
            em.getTransaction().commit();
        }
        if (removeEmptyAuthors != null) {
            em.getTransaction().begin();
            List<AuthorEntity> authorsToDelete = em.createQuery("from AuthorEntity  where books.size = 0").getResultList();
            for (AuthorEntity authorToDelete : authorsToDelete) {
                em.remove(authorToDelete);
            }
            em.getTransaction().commit();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<AuthorEntity> authors;
        authors = em.createQuery("from AuthorEntity").getResultList();
        String shouldSendJson = request.getParameter("json");
        if (shouldSendJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(authors);
            PrintWriter out = response.getWriter();
            out.print(jsonInString);
        } else {
            request.setAttribute("authors", authors);
        }
    }
}
