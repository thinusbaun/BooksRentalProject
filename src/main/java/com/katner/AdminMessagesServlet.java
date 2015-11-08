package com.katner;

import com.katner.model.AdminMessageEntity;
import com.katner.util.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Iterator;
import java.util.List;

/**
 * Created by michal on 04.11.15.
 */
@WebServlet(name = "AdminMessagesServlet")
public class AdminMessagesServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String deleteMessageIdString = request.getParameter("deleteMessageId");
        if (deleteMessageIdString != null) {
            Integer id = Integer.parseInt(deleteMessageIdString);
            AdminMessageEntity entity = new AdminMessageEntity();
            entity.setId(id);
            EntityManager em = EntityManagerHelper.getEntityManager();
            em.getTransaction().begin();
            em.remove(em.merge(entity));
            em.getTransaction().commit();
            em.close();
            return;
        }
        String newMessageContent = request.getParameter("newMessageContent");
        if (newMessageContent != null) {
            return;
        }
        String closeMessageIdString = request.getParameter("closeMessageId");
        if (closeMessageIdString != null) {
            Integer closedMessageId = Integer.parseInt(closeMessageIdString);
            HttpSession session = request.getSession();
            ArrayList<Integer> closedMessages = (ArrayList<Integer>) session.getAttribute("closedMessages");
            if (closedMessages == null) {
                closedMessages = new ArrayList<Integer>();
            }
        closedMessages.add(closedMessageId);
        session.setAttribute("closedMessages", closedMessages);
        }

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        List<AdminMessageEntity> messagesList = entityManager.createQuery("from AdminMessageEntity").getResultList();
        HttpSession session = request.getSession();
        ArrayList<Integer> closedMessages = (ArrayList<Integer>) session.getAttribute("closedMessages");
        if (closedMessages != null) {
            for (Iterator<AdminMessageEntity> iterator = messagesList.iterator(); iterator.hasNext(); ) {
                AdminMessageEntity message = iterator.next();
                if (closedMessages.contains(new Integer(message.getId()))) {
                    iterator.remove();
                }
            }
        }

        request.setAttribute("messages", messagesList);

    }
}
