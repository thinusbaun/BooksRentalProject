package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 25.10.15.
 */
@Entity
@Table(name = "Ksiazka", schema = "", catalog = "mydb")
public class KsiazkaEntity {
    private int idKsiazka;
    private String tytul;

    @Id
    @Column(name = "idKsiazka")
    public int getIdKsiazka() {
        return idKsiazka;
    }

    public void setIdKsiazka(int idKsiazka) {
        this.idKsiazka = idKsiazka;
    }

    @Basic
    @Column(name = "Tytul")
    public String getTytul() {
        return tytul;
    }

    public void setTytul(String tytul) {
        this.tytul = tytul;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        KsiazkaEntity that = (KsiazkaEntity) o;

        if (idKsiazka != that.idKsiazka) return false;
        if (tytul != null ? !tytul.equals(that.tytul) : that.tytul != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idKsiazka;
        result = 31 * result + (tytul != null ? tytul.hashCode() : 0);
        return result;
    }
}
