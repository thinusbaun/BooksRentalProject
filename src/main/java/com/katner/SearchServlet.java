package com.katner;

import com.katner.model.BookEntity;
import com.katner.util.EntityManagerHelper;
import org.apache.lucene.analysis.core.SimpleAnalyzer;
import org.apache.lucene.queryparser.classic.MultiFieldQueryParser;
import org.apache.lucene.queryparser.classic.ParseException;
import org.apache.lucene.queryparser.classic.QueryParser;
import org.hibernate.search.jpa.FullTextEntityManager;
import org.hibernate.search.query.dsl.QueryBuilder;

import javax.persistence.EntityManager;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.ArrayList;
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
        ArrayList<String> searchFields = new ArrayList<String>();
        if (request.getParameter("title") != null) {
            searchFields.add("title");
        }
        if (request.getParameter("isbn") != null) {
            searchFields.add("isbn");
        }
        if (request.getParameter("author") != null) {
            searchFields.add("authors.name"); //TODO: zmiana "author:" z zapytania na authors.name?
        }
        if (request.getParameter("tag") != null) {
            searchFields.add("tags.title"); //TODO: zmiana "tag:" z zapytania na tags.title?
        }

        if (searchQuery != null) {
            EntityManager em = EntityManagerHelper.getEntityManager();
            FullTextEntityManager fullTextEntityManager =
                    org.hibernate.search.jpa.Search.getFullTextEntityManager(em);
            em.getTransaction().begin();
            QueryBuilder qb = fullTextEntityManager.getSearchFactory()
                    .buildQueryBuilder().forEntity(BookEntity.class).get();

            org.apache.lucene.search.Query luceneQuery = null;
            try {
                MultiFieldQueryParser parser = new MultiFieldQueryParser(searchFields.toArray(new String[searchFields.size()]),
                        new SimpleAnalyzer());
                parser.setDefaultOperator(QueryParser.Operator.AND);
                luceneQuery = parser.parse(searchQuery);
            } catch (ParseException e) {
                e.printStackTrace(); //TODO: zwracać error do jsp
            }


            javax.persistence.Query jpaQuery =
                    fullTextEntityManager.createFullTextQuery(luceneQuery, BookEntity.class);

            List result = jpaQuery.getResultList();

            em.getTransaction().commit();
            request.setAttribute("books", result);
//            em.close();

        }
    }
}
