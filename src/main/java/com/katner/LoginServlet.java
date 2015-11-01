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
 * Created by michal on 28.10.15.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager entityManager = factory.createEntityManager();
        List<AuthUserEntity> results = entityManager.createQuery("SELECT t FROM AuthUserEntity t where t.username = :username")
                .setParameter("username", request.getParameter("login")).getResultList();
        if (results.size() != 0) {
            AuthUserEntity czytelnikEntity = (AuthUserEntity) results.get(0);
            if (czytelnikEntity.getPassword().equals(request.getParameter("haslo"))) { //TODO: Hashowanie hasła
                Cookie cookie = new Cookie("imie", czytelnikEntity.getFirstName());
                cookie.setPath("/");
                cookie.setMaxAge(9000);
                response.addCookie(cookie);
                cookie = new Cookie("login", request.getParameter("login"));
                cookie.setPath("/");
                cookie.setMaxAge(9000);
                response.addCookie(cookie);
                response.sendRedirect("/");

            } else {
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
