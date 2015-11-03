package com.katner;


import com.katner.model.AuthUserEntity;
import com.katner.util.EntityManagerHelper;
import com.katner.util.Hasher;

import javax.persistence.EntityManager;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;

/**
 * Created by michal on 28.10.15.
 */
@WebServlet(name = "LoginServlet")
public class LoginServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        HttpSession session = request.getSession();
        List<AuthUserEntity> results = entityManager.createQuery("SELECT t FROM AuthUserEntity t where t.username = :username")
                .setParameter("username", request.getParameter("login")).getResultList();
        if (results.size() != 0) {
            AuthUserEntity userEntity = (AuthUserEntity) results.get(0);
            if (Hasher.checkPassword(request.getParameter("haslo"), userEntity.getPassword())) {
                session.setAttribute("imie", userEntity.getFirstName());
                session.setAttribute("login", userEntity.getUsername());
                session.setAttribute("user", userEntity);
                response.sendRedirect("/");

            } else {
                session.setAttribute("message", "Złe hasło.");
                RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
                dispatcher.forward(request, response);
            }
        } else {
            session.setAttribute("message", "Podany użytkownik nie istnieje.");
            RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
            dispatcher.forward(request, response);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("login.jsp");
        dispatcher.forward(request, response);
    }
}
