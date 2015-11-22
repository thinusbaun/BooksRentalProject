package com.katner;

import com.katner.model.*;
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
        String tagToRemoveId = request.getParameter("tagToRemoveId");
        String authorToRemoveId = request.getParameter("authorToRemoveId");
        String addAuthorId = request.getParameter("addAuthorId");
        String addTagId = request.getParameter("addTagId");

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
        if (tagToRemoveId != null) {
            em.getTransaction().begin();
            javax.persistence.Query q = em.createQuery("delete BookTagsEntity where bookId = :bookid and tagId = :tagid")
                    .setParameter("bookid", Integer.parseInt(request.getParameter("tagToRemoveBookId")))
                    .setParameter("tagid", Integer.parseInt(tagToRemoveId));
            q.executeUpdate();
            em.getTransaction().commit();
        }
        if (authorToRemoveId != null) {
            em.getTransaction().begin();
            javax.persistence.Query q = em.createQuery("delete BookAuthorsEntity where bookId = :bookid and authorId = :authorid")
                    .setParameter("bookid", Integer.parseInt(request.getParameter("authorToRemoveBookId")))
                    .setParameter("authorid", Integer.parseInt(authorToRemoveId));
            q.executeUpdate();
            em.getTransaction().commit();
        }
        if (addAuthorId != null) {
            em.getTransaction().begin();
            AuthorEntity author = em.find(AuthorEntity.class, Integer.parseInt(addAuthorId));
            BookEntity book = em.find(BookEntity.class, Integer.parseInt(request.getParameter("bookId")));
            if (author != null && book != null) {
                List<AuthorEntity> bookAuthors = book.getAuthors();
                bookAuthors.add(author);
                book.setAuthors(bookAuthors);
            }
            em.getTransaction().commit();
        }
        if (addTagId != null) {
            em.getTransaction().begin();
            TagEntity tag = em.find(TagEntity.class, Integer.parseInt(addTagId));
            BookEntity book = em.find(BookEntity.class, Integer.parseInt(request.getParameter("bookId")));
            if (tag != null && book != null) {
                List<TagEntity> bookTags = book.getTags();
                bookTags.add(tag);
                book.setTags(bookTags);
            }
            em.getTransaction().commit();
        }
        response.setStatus(HttpServletResponse.SC_OK);
    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
    }
}
