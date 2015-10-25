package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 25.10.15.
 */
@Entity
@Table(name = "Autor", schema = "", catalog = "mydb")
public class AutorEntity {
    private int idAutor;
    private String nazwisko;
    private String imie;

    @Id
    @Column(name = "idAutor")
    public int getIdAutor() {
        return idAutor;
    }

    public void setIdAutor(int idAutor) {
        this.idAutor = idAutor;
    }

    @Basic
    @Column(name = "Nazwisko")
    public String getNazwisko() {
        return nazwisko;
    }

    public void setNazwisko(String nazwisko) {
        this.nazwisko = nazwisko;
    }

    @Basic
    @Column(name = "Imie")
    public String getImie() {
        return imie;
    }

    public void setImie(String imie) {
        this.imie = imie;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AutorEntity that = (AutorEntity) o;

        if (idAutor != that.idAutor) return false;
        if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idAutor;
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        return result;
    }
}
