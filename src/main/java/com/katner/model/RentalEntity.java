package com.katner.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "rental", schema = "wypozyczalnia", catalog = "")
public class RentalEntity {
    private int id;
    private Date rentalDate;
    private Date returnDate;
    private BookCopyEntity bookCopy;
    private AuthUserEntity user;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "rentalDate", nullable = true)
    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Basic
    @Column(name = "returnDate", nullable = true)
    public Date getReturnDate() {
        return returnDate;
    }

    public void setReturnDate(Date returnDate) {
        this.returnDate = returnDate;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        RentalEntity that = (RentalEntity) o;

        if (id != that.id) return false;
        if (rentalDate != null ? !rentalDate.equals(that.rentalDate) : that.rentalDate != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (rentalDate != null ? rentalDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "bookCopyId", referencedColumnName = "id", nullable = false)
    public BookCopyEntity getBookCopy() {
        return bookCopy;
    }

    public void setBookCopy(BookCopyEntity bookCopy) {
        this.bookCopy = bookCopy;
    }

    @ManyToOne
    @JoinColumn(name = "userId", referencedColumnName = "id", nullable = false)
    public AuthUserEntity getUser() {
        return user;
    }

    public void setUser(AuthUserEntity user) {
        this.user = user;
    }
}
