package com.katner.model;

import javax.persistence.Column;
import javax.persistence.Id;
import java.io.Serializable;

/**
 * Created by michal on 25.10.15.
 */
public class AutorzyEntityPK implements Serializable {
    private int autorIdAutor;
    private int ksiazkaIdKsiazka;

    @Column(name = "Autor_idAutor")
    @Id
    public int getAutorIdAutor() {
        return autorIdAutor;
    }

    public void setAutorIdAutor(int autorIdAutor) {
        this.autorIdAutor = autorIdAutor;
    }

    @Column(name = "Ksiazka_idKsiazka")
    @Id
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

        AutorzyEntityPK that = (AutorzyEntityPK) o;

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
