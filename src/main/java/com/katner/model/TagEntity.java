package com.katner.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by michal on 08.11.15.
 */
@Entity
@Table(name = "tag", schema = "wypozyczalnia", catalog = "")
public class TagEntity {
    private List<BookEntity> books;
    private int id;
    private String title;

    @ManyToMany(mappedBy = "tags")
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
    @Column(name = "title")
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TagEntity tagEntity = (TagEntity) o;

        if (id != tagEntity.id) return false;
        if (title != null ? !title.equals(tagEntity.title) : tagEntity.title != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        return result;
    }
}
