package com.katner.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by michal on 01.11.15.
 */
@Entity
@Table(name = "rental", schema = "", catalog = "wypozyczalnia")
public class RentalEntity {
    private int id;
    private int userId;
    private int bookCopyId;
    private Date rentalDate;
    private Date returnDate;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "userId")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "bookCopyId")
    public int getBookCopyId() {
        return bookCopyId;
    }

    public void setBookCopyId(int bookCopyId) {
        this.bookCopyId = bookCopyId;
    }

    @Basic
    @Column(name = "rentalDate")
    public Date getRentalDate() {
        return rentalDate;
    }

    public void setRentalDate(Date rentalDate) {
        this.rentalDate = rentalDate;
    }

    @Basic
    @Column(name = "returnDate")
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
        if (userId != that.userId) return false;
        if (bookCopyId != that.bookCopyId) return false;
        if (rentalDate != null ? !rentalDate.equals(that.rentalDate) : that.rentalDate != null) return false;
        if (returnDate != null ? !returnDate.equals(that.returnDate) : that.returnDate != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + bookCopyId;
        result = 31 * result + (rentalDate != null ? rentalDate.hashCode() : 0);
        result = 31 * result + (returnDate != null ? returnDate.hashCode() : 0);
        return result;
    }
}
