package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 24.10.15.
 */
@Entity
@Table(name = "penguins", schema = "", catalog = "test")
public class PenguinsEntity {
    private int id;
    private String myval;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "myval")
    public String getMyval() {
        return myval;
    }

    public void setMyval(String myval) {
        this.myval = myval;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        PenguinsEntity that = (PenguinsEntity) o;

        if (id != that.id) return false;
        if (myval != null ? !myval.equals(that.myval) : that.myval != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (myval != null ? myval.hashCode() : 0);
        return result;
    }
}
