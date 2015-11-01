package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 01.11.15.
 */
@Entity
@Table(name = "bookCopy", schema = "", catalog = "wypozyczalnia")
public class BookCopyEntity {
    private int id;
    private int bookId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "bookId")
    public int getBookId() {
        return bookId;
    }

    public void setBookId(int bookId) {
        this.bookId = bookId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCopyEntity that = (BookCopyEntity) o;

        if (id != that.id) return false;
        if (bookId != that.bookId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + bookId;
        return result;
    }
}
