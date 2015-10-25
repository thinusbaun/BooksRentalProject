package com.katner;

import com.katner.model.PenguinsEntity;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.Persistence;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

/**
 * Created by michal on 23.10.15.
 */
@WebServlet(name = "Servlet1")
public class Servlet1 extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

        EntityManagerFactory factory = Persistence.createEntityManagerFactory("NewPersistenceUnit");
        EntityManager manager = factory.createEntityManager();
        List<PenguinsEntity> penguinsEntityList = manager.createQuery("select e from PenguinsEntity e").getResultList();
        PrintWriter writer = response.getWriter();
        writer.write("Witam pana");
        for (PenguinsEntity penguin : penguinsEntityList)
        {
            writer.write("\n");
            writer.write(penguin.getMyval());
        }
    }
}