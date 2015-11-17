package com.katner;

import com.katner.model.BookCopyEntity;
import com.katner.model.BookEntity;
import com.katner.model.RentalEntity;
import com.katner.util.EntityManagerHelper;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Calendar;
import java.util.List;

/**
 * Created by michal on 16.11.15.
 */
@WebServlet(name = "BookManageServlet")
public class BookManageServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String shouldAddBook = request.getParameter("shouldAddBook");
        String removeBookId = request.getParameter("removeBookId");
        String removeEmptyBooks = request.getParameter("removeEmptyBooks");
        EntityManager em = EntityManagerHelper.getEntityManager();
        if (shouldAddBook != null) {
            BookEntity book = new BookEntity();
            book.setTitle(request.getParameter("bookTitle"));
            book.setIsbn(request.getParameter("bookIsbn"));
            book.setAddedDate(new java.sql.Date(Calendar.getInstance().getTime().getTime()));
            em.getTransaction().begin();
            em.persist(book);
            em.getTransaction().commit();
        }
        if (removeBookId != null) {
            BookEntity book = null;
            book = em.find(BookEntity.class, Integer.parseInt(removeBookId));
            em.getTransaction().begin();
            javax.persistence.Query q = em.createQuery("delete BookAuthorsEntity where bookId = :bookid").setParameter("bookid", Integer.parseInt(removeBookId));
            q.executeUpdate();
            em.remove(book);
            em.getTransaction().commit();
        }
        if (removeEmptyBooks != null) {
            em.getTransaction().begin();
            List<BookEntity> booksToDelete = em.createQuery("from BookEntity  where authors.size = 0").getResultList();
            List<BookCopyEntity> copies = em.createQuery("from BookCopyEntity where book in :books").setParameter("books", booksToDelete).getResultList();
            List<RentalEntity> rentals = em.createQuery("from RentalEntity where bookCopy in :copies").setParameter("copies", copies).getResultList();
            for (RentalEntity rental : rentals) {
                em.remove(rental);
            }
            for (BookCopyEntity copy : copies) {
                em.remove(copy);
            }

            for (BookEntity bookToDelete : booksToDelete) {
                em.remove(bookToDelete);
            }
            em.getTransaction().commit();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
