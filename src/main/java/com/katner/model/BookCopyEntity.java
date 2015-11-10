package com.katner.model;

import javax.persistence.*;
import java.util.List;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "bookCopy", schema = "wypozyczalnia", catalog = "")
public class BookCopyEntity {
    private int id;
    private BookEntity book;
    private List<RentalEntity> rentals;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        BookCopyEntity that = (BookCopyEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @ManyToOne
    @JoinColumn(name = "bookId", referencedColumnName = "id", nullable = false)
    public BookEntity getBook() {
        return book;
    }

    public void setBook(BookEntity book) {
        this.book = book;
    }

    @OneToMany(mappedBy = "bookCopy")
    public List<RentalEntity> getRentals() {
        return rentals;
    }

    public void setRentals(List<RentalEntity> rentals) {
        this.rentals = rentals;
    }
}
