package com.katner;

import com.katner.model.AuthUserEntity;
import com.katner.model.BookCopyEntity;
import com.katner.model.RentalEntity;
import com.katner.util.EntityManagerHelper;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Restrictions;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by michal on 09.11.15.
 */
@WebServlet(name = "BookRentServlet")
public class BookRentServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        HttpSession session = request.getSession();
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        String returnRentalIdString = request.getParameter("returnRentalId");
        BookCopyEntity bookCopy;
        if (returnRentalIdString != null) {
            RentalEntity rental = entityManager.find(RentalEntity.class, Integer.parseInt(returnRentalIdString));
            rental.setReturnDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            entityManager.getTransaction().begin();
            entityManager.merge(rental);
            entityManager.getTransaction().commit();
            return;
        }
        bookCopy = entityManager.find(BookCopyEntity.class, Integer.parseInt(request.getParameter("rentBookId")));
        if (bookCopy != null) {
            RentalEntity rentalEntity = new RentalEntity();
            rentalEntity.setBookCopy(bookCopy);
            AuthUserEntity user = (AuthUserEntity) session.getAttribute("user");
            rentalEntity.setUser(user);
            rentalEntity.setRentalDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            entityManager.getTransaction().begin();
            entityManager.persist(rentalEntity);
            entityManager.getTransaction().commit();
        }
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String userIdString = request.getParameter("userId");
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (userIdString != null) {
            Session session = em.unwrap(Session.class);
            Criteria criteria = session.createCriteria(RentalEntity.class, "rental");
            List<RentalEntity> rentals = criteria.add(Restrictions.eq("user.id", Integer.parseInt(userIdString))).add(Restrictions.isNull("returnDate")).list();
            request.setAttribute("rentals", rentals);
        }

    }
}
