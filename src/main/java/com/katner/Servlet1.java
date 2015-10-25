package com.katner;

import com.katner.model.AutorEntity;
import com.katner.model.AutorzyEntity;
import com.katner.model.KsiazkaEntity;
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
        List<KsiazkaEntity> penguinsEntityList = manager.createQuery("select e from KsiazkaEntity e").getResultList();
        PrintWriter writer = response.getWriter();

        writer.write("Witam pana");
        for (KsiazkaEntity penguin : penguinsEntityList)
        {
            writer.write("\n");
            writer.write(penguin.getTytul() + "  -  ");
            List<AutorEntity> autorEntities = manager.createQuery("select distinct A from AutorEntity A, AutorzyEntity B, KsiazkaEntity C where A.idAutor = B.autorIdAutor and B.ksiazkaIdKsiazka =" + penguin.getIdKsiazka()).getResultList();
            if (autorEntities.size() > 0) {
                AutorEntity tmp = autorEntities.get(0);
                writer.write(autorEntities.get(0).getNazwisko());
            } else
            {
                writer.write("Autor nieznany");
            }
            writer.write("\n");
        }
    }
}