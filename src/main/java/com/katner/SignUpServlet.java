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
import java.io.IOException;
import java.sql.Timestamp;
import java.util.Calendar;
import java.util.List;

/**
 * Created by michal on 25.10.15.
 */
@WebServlet(name = "SignUpServlet")
public class SignUpServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        List<AuthUserEntity> entries = entityManager.createQuery("Select a from AuthUserEntity a where a.username = :username").setParameter("username", request.getParameter("login")).getResultList();
        if (entries.size() == 0) {
            AuthUserEntity userEntity = new AuthUserEntity();
            userEntity.setFirstName(request.getParameter("imie"));
            userEntity.setLastName(request.getParameter("nazwisko"));
            userEntity.setUsername(request.getParameter("login"));
            userEntity.setPassword(Hasher.encode(request.getParameter("haslo")));
            userEntity.setEmail(request.getParameter("email"));
            userEntity.setDateJoined(new Timestamp(Calendar.getInstance().getTime().getTime()));
            entityManager.getTransaction().begin();
            entityManager.persist(userEntity);
            entityManager.getTransaction().commit();
            entityManager.close();
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
