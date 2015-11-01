package com.katner;

import com.katner.model.AuthUserEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.Cookie;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;

/**
 * Created by michal on 25.10.15.
 */
@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();
        List<AuthUserEntity> entries = entityManager.createQuery("Select a from AuthUserEntity a where a.username = :login").setParameter("username", request.getParameter("login")).getResultList();
        if (entries.size() == 0) {
            AuthUserEntity czytelnikEntity = new AuthUserEntity();
            czytelnikEntity.setFirstName(request.getParameter("imie"));
            czytelnikEntity.setLastName(request.getParameter("nazwisko"));
            czytelnikEntity.setUsername(request.getParameter("login"));
            czytelnikEntity.setPassword(request.getParameter("haslo")); //TODO: Hashowanie hasła
            czytelnikEntity.setEmail(request.getParameter("email"));
            entityManager.getTransaction().begin();
            entityManager.persist(czytelnikEntity);
            entityManager.getTransaction().commit();
            entityManager.close();
            Cookie cookie = new Cookie("imie", request.getParameter("imie"));
            cookie.setPath("/");
            cookie.setMaxAge(9000);
            response.addCookie(cookie);
            cookie = new Cookie("login", request.getParameter("login"));
            cookie.setPath("/");
            cookie.setMaxAge(9000);
            response.addCookie(cookie);
            response.sendRedirect("/");
        } else {
            request.setAttribute("signupsuccessfull", false);
            RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
            dispatcher.forward(request, response);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
        dispatcher.forward(request, response);
    }

}
