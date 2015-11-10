package com.katner;

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
import java.io.IOException;
import java.util.Iterator;
import java.util.List;

/**
 * Created by michal on 09.11.15.
 */
@WebServlet(name = "BookCopyServlet")
public class BookCopyServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        EntityManager entityManager = EntityManagerHelper.getEntityManager();
        List<BookCopyEntity> books = null;
        String bookIdString = request.getParameter("bookId");
        if (!bookIdString.isEmpty()) {
            Session session = entityManager.unwrap(Session.class);
            Criteria criteria = session.createCriteria(BookCopyEntity.class, "bookCopy").createAlias("book", "b");
            books = criteria.add(Restrictions.eq("b.id", Integer.parseInt(bookIdString))).list();
            for (Iterator<BookCopyEntity> it = books.iterator(); it.hasNext(); ) {
                BookCopyEntity entity = it.next();
                List<RentalEntity> rentals = entity.getRentals();
                if (rentals.isEmpty()) {
                    continue;
                }
                for (Iterator<RentalEntity> rentalIt = rentals.iterator(); rentalIt.hasNext(); ) {
                    RentalEntity rental = rentalIt.next();
                    if (rental.getReturnDate() == null) {
                        it.remove();
                    }
                }
            }

        }
        request.setAttribute("copies", books);
    }
}
