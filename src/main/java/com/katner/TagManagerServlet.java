package com.katner;

import com.fasterxml.jackson.databind.ObjectMapper;
import com.katner.model.TagEntity;
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
 * Created by michal on 20.11.15.
 */
@WebServlet(name = "TagManagerServlet")
public class TagManagerServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String tagTitleToAdd = request.getParameter("tagTitleToAdd");
        String removeTagId = request.getParameter("removeTagId");
        String removeEmptyTags = request.getParameter("removeEmptyTags");
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (tagTitleToAdd != null) {
            TagEntity author = new TagEntity();
            author.setTitle(tagTitleToAdd);
            em.getTransaction().begin();
            em.persist(author);
            em.getTransaction().commit();
        }
        if (removeTagId != null) {
            TagEntity author = em.find(TagEntity.class, Integer.parseInt(removeTagId));
            em.getTransaction().begin();
            javax.persistence.Query q = em.createQuery("delete BookTagsEntity where tagId = :tagid").setParameter("tagid", Integer.parseInt(removeTagId));
            q.executeUpdate();
            em.remove(author);
            em.getTransaction().commit();
        }
        if (removeEmptyTags != null) {
            em.getTransaction().begin();
            List<TagEntity> tagsToDelete = em.createQuery("from TagEntity where books.size = 0").getResultList();
            for (TagEntity tagToDelete : tagsToDelete) {
                em.remove(tagToDelete);
            }
            em.getTransaction().commit();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager em = EntityManagerHelper.getEntityManager();
        List<TagEntity> tags;
        tags = em.createQuery("from TagEntity").getResultList();
        String shouldSendJson = request.getParameter("json");
        if (shouldSendJson != null) {
            ObjectMapper mapper = new ObjectMapper();
            String jsonInString = mapper.writeValueAsString(tags);
            PrintWriter out = response.getWriter();
            out.print(jsonInString);
        } else {
            request.setAttribute("tags", tags);
        }
    }
}
