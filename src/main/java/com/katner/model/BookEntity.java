package com.katner.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by michal on 08.11.15.
 */
@Entity
@Table(name = "book", schema = "wypozyczalnia", catalog = "")
public class BookEntity {
    private List<AuthorEntity> authors;
    private List<TagEntity> tags;
    private int id;
    private String title;
    private String isbn;

    @ManyToMany
    @JoinTable(name = "book_authors", catalog = "", schema = "wypozyczalnia", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "author_id", referencedColumnName = "id", nullable = false))
    public List<AuthorEntity> getAuthors() {
        return authors;
    }

    public void setAuthors(List<AuthorEntity> authors) {
        this.authors = authors;
    }

    @ManyToMany
    @JoinTable(name = "book_tags", catalog = "", schema = "wypozyczalnia", joinColumns = @JoinColumn(name = "book_id", referencedColumnName = "id", nullable = false), inverseJoinColumns = @JoinColumn(name = "tag_id", referencedColumnName = "id", nullable = false))
    public List<TagEntity> getTags() {
        return tags;
    }

    public void setTags(List<TagEntity> tags) {
        this.tags = tags;
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

    @Basic
    @Column(name = "isbn")
    public String getIsbn() {
        return isbn;
    }

    public void setIsbn(String isbn) {
        this.isbn = isbn;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookEntity that = (BookEntity) o;

        if (id != that.id) return false;
        if (title != null ? !title.equals(that.title) : that.title != null) return false;
        if (isbn != null ? !isbn.equals(that.isbn) : that.isbn != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (isbn != null ? isbn.hashCode() : 0);
        return result;
    }
}
