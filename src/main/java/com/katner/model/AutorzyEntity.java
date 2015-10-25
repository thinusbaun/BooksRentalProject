package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 25.10.15.
 */
@Entity
@Table(name = "Autorzy", schema = "", catalog = "mydb")
@IdClass(AutorzyEntityPK.class)
public class AutorzyEntity {
    private int autorIdAutor;
    private int ksiazkaIdKsiazka;

    @Id
    @Column(name = "Autor_idAutor")
    public int getAutorIdAutor() {
        return autorIdAutor;
    }

    public void setAutorIdAutor(int autorIdAutor) {
        this.autorIdAutor = autorIdAutor;
    }

    @Id
    @Column(name = "Ksiazka_idKsiazka")
    public int getKsiazkaIdKsiazka() {
        return ksiazkaIdKsiazka;
    }

    public void setKsiazkaIdKsiazka(int ksiazkaIdKsiazka) {
        this.ksiazkaIdKsiazka = ksiazkaIdKsiazka;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutorzyEntity that = (AutorzyEntity) o;

        if (autorIdAutor != that.autorIdAutor) return false;
        if (ksiazkaIdKsiazka != that.ksiazkaIdKsiazka) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = autorIdAutor;
        result = 31 * result + ksiazkaIdKsiazka;
        return result;
    }
}
