package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 25.10.15.
 */
@Entity
@Table(name = "Czytelnik", schema = "", catalog = "mydb")
public class CzytelnikEntity {
    private int idCzytelnik;
    private String nazwisko;
    private String imie;
    private String email;
    private String login;
    private String haslo;

    @Id
    @Column(name = "idCzytelnik")
    public int getIdCzytelnik() {
        return idCzytelnik;
    }

    public void setIdCzytelnik(int idCzytelnik) {
        this.idCzytelnik = idCzytelnik;
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

    @Basic
    @Column(name = "Email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "Login")
    public String getLogin() {
        return login;
    }

    public void setLogin(String login) {
        this.login = login;
    }

    @Basic
    @Column(name = "Haslo")
    public String getHaslo() {
        return haslo;
    }

    public void setHaslo(String haslo) {
        this.haslo = haslo;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CzytelnikEntity that = (CzytelnikEntity) o;

        if (idCzytelnik != that.idCzytelnik) return false;
        if (nazwisko != null ? !nazwisko.equals(that.nazwisko) : that.nazwisko != null) return false;
        if (imie != null ? !imie.equals(that.imie) : that.imie != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (login != null ? !login.equals(that.login) : that.login != null) return false;
        if (haslo != null ? !haslo.equals(that.haslo) : that.haslo != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idCzytelnik;
        result = 31 * result + (nazwisko != null ? nazwisko.hashCode() : 0);
        result = 31 * result + (imie != null ? imie.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (login != null ? login.hashCode() : 0);
        result = 31 * result + (haslo != null ? haslo.hashCode() : 0);
        return result;
    }
}
