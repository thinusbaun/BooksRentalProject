package com.katner;

import com.katner.model.AuthUserEntity;
import com.katner.model.BookEntity;
import com.katner.model.SearchEntryEntity;
import com.katner.util.EntityManagerHelper;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import java.io.IOException;
import java.util.List;


/**
 * Created by michal on 13.11.15.
 */
@WebServlet(name = "SearchServlet")
public class SearchServlet extends HttpServlet {
    protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

    }

    protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
        String searchQuery = request.getParameter("q");

        if (searchQuery != null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            FullTextEntityManager fullTextEntityManager =
                    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
            em.getTransaction().begin();
            QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(BookEntity.class).get();
            org.apache.lucene.search.Query luceneQuery = qb
                    .keyword()
                    .onFields("title", "isbn", "authors.name", "tags.title")
                    .matching(searchQuery)
                    .createQuery();

            javax.persistence.Query jpaQuery =
                    fullTextEntityManager.createFullTextQuery(luceneQuery, BookEntity.class);

            List result = jpaQuery.getResultList();

            request.setAttribute("searchResult", result);

            HttpSession session = request.getSession();
            AuthUserEntity user = (AuthUserEntity) session.getAttribute("user");
            SearchEntryEntity searchEntry = new SearchEntryEntity();

            searchEntry.setText(searchQuery);
            searchEntry.setId(user.getId());
            em.getTransaction().commit();
            em.close();

        }
    }
}
