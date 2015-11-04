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
import java.io.BufferedReader;
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
        BufferedReader reader = request.getReader();
        String tmp = reader.readLine();
        Integer closedMessageId = Integer.parseInt(tmp);
        HttpSession session = request.getSession();
        ArrayList<Integer> closedMessages = (ArrayList<Integer>) session.getAttribute("closedMessages");
        if (closedMessages == null) {
            closedMessages = new ArrayList<Integer>();
        }
        closedMessages.add(closedMessageId);
        session.setAttribute("closedMessages", closedMessages);


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
