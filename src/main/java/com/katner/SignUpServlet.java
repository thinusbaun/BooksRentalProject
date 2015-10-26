package com.katner;

import com.katner.model.CzytelnikEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.RequestDispatcher;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
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
        List<CzytelnikEntity> entries = entityManager.createQuery("Select a from CzytelnikEntity a where a.login = :login").setParameter("login", request.getParameter("login")).getResultList();
        if (entries.size() == 0) {
            CzytelnikEntity czytelnikEntity = new CzytelnikEntity();
            czytelnikEntity.setImie(request.getParameter("imie"));
            czytelnikEntity.setNazwisko(request.getParameter("nazwisko"));
            czytelnikEntity.setLogin(request.getParameter("login"));
            czytelnikEntity.setHaslo(request.getParameter("haslo"));
            entityManager.getTransaction().begin();
            entityManager.persist(czytelnikEntity);
            entityManager.getTransaction().commit();
            entityManager.close();
        } else {
            response.sendError(444);
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        RequestDispatcher dispatcher = request.getRequestDispatcher("signup.jsp");
        dispatcher.forward(request, response);
    }
}
