package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 01.11.15.
 */
@Entity
@Table(name = "searchEntry", schema = "", catalog = "wypozyczalnia")
public class SearchEntryEntity {
    private int id;
    private int userId;
    private String text;

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
    @Column(name = "text")
    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SearchEntryEntity that = (SearchEntryEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
    }
}
