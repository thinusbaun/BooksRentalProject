package com.katner.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by michal on 08.11.15.
 */
@Entity
@Table(name = "author", schema = "wypozyczalnia", catalog = "")
public class AuthorEntity {
    private List<BookEntity> books;
    private int id;
    private String name;

    @ManyToMany(mappedBy = "authors")
    public List<BookEntity> getBooks() {
        return books;
    }

    public void setBooks(List<BookEntity> books) {
        this.books = books;
    }

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "name")
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthorEntity that = (AuthorEntity) o;

        if (id != that.id) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        return result;
    }
}
