package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "searchEntry", schema = "wypozyczalnia", catalog = "")
public class SearchEntryEntity {
    private int id;
    private String text;
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
    @Column(name = "text", nullable = false, length = 120)
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
        if (text != null ? !text.equals(that.text) : that.text != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (text != null ? text.hashCode() : 0);
        return result;
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
