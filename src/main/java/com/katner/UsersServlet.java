package com.katner;

import com.katner.model.AuthUserEntity;
import com.katner.util.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.List;
import java.util.Objects;

/**
 * Created by michal on 08.11.15.
 */
@WebServlet(name = "UsersServlet")
public class UsersServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String id = request.getParameter("userId");
        String setIsActiveString = request.getParameter("setIsActive");
        if (setIsActiveString != null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            AuthUserEntity entity = em.find(AuthUserEntity.class, Integer.parseInt(id));
            if (Objects.equals(setIsActiveString, "true")) {
                entity.setIsActive((byte) 1);
            } else {
                entity.setIsActive((byte) 0);
            }
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return;
        }

        String setIsStaffString = request.getParameter("setIsStaff");
        if (setIsStaffString != null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            AuthUserEntity entity = em.find(AuthUserEntity.class, Integer.parseInt(id));
            if (Objects.equals(setIsStaffString, "true")) {
                entity.setIsStaff((byte) 1);
            } else {
                entity.setIsStaff((byte) 0);
            }
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return;
        }

        String setIsSuperuserString = request.getParameter("setIsSuperuser");
        if (setIsSuperuserString != null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            AuthUserEntity entity = em.find(AuthUserEntity.class, Integer.parseInt(id));
            if (Objects.equals(setIsSuperuserString, "true")) {
                entity.setIsSuperuser((byte) 1);
            } else {
                entity.setIsSuperuser((byte) 0);
            }
            em.getTransaction().begin();
            em.merge(entity);
            em.getTransaction().commit();
            return;
        }
        String shouldDeleteUser = request.getParameter("deleteUser");
        if (shouldDeleteUser != null) {
            AuthUserEntity entity = new AuthUserEntity();
            entity.setId(Integer.parseInt(id));
            EntityManager em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
            em.close();
            return;
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<AuthUserEntity> users = em.createQuery("from AuthUserEntity").getResultList();
        request.setAttribute("users", users);

    }
}
