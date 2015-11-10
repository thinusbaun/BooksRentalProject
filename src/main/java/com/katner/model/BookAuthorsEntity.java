package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "book_authors", schema = "wypozyczalnia", catalog = "")
public class BookAuthorsEntity {
    private int id;
    private int bookId;
    private int authorId;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "book_id", nullable = false)
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Basic
    @Column(name = "author_id", nullable = false)
    public int getAuthorId() {
        return authorId;
    }

    public void setAuthorId(int authorId) {
        this.authorId = authorId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookAuthorsEntity that = (BookAuthorsEntity) o;

        if (id != that.id) return false;
        if (bookId != that.bookId) return false;
        if (authorId != that.authorId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        result = 31 * result + authorId;
        return result;
    }
}
