package com.katner.model;

import javax.persistence.*;
import java.sql.Date;
import java.util.List;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "book", schema = "wypozyczalnia", catalog = "")
public class BookEntity {
    private int id;
    private String title;
    private String isbn;
    private List<AuthorEntity> authors;
    private List<TagEntity> tags;
    private List<BookCopyEntity> copies;
    private Date addedDate;

    @Basic
    @Column(name = "addedDate", nullable = true)
    public Date getAddedDate() {
        return addedDate;
    }

    public void setAddedDate(Date addedDate) {
        this.addedDate = addedDate;
    }

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "title", nullable = false, length = 45)
    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    @Basic
    @Column(name = "isbn", nullable = false, length = 15)
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

    @OneToMany(mappedBy = "book")
    public List<BookCopyEntity> getCopies() {
        return copies;
    }

    public void setCopies(List<BookCopyEntity> copies) {
        this.copies = copies;
    }
}
