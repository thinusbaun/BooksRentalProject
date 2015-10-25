package com.katner.model;

import javax.persistence.*;
import java.sql.Date;

/**
 * Created by michal on 25.10.15.
 */
@Entity
@Table(name = "Wypozyczenie", schema = "", catalog = "mydb")
public class WypozyczenieEntity {
    private int idWypozyczenie;
    private Date dataWypozyczenia;

    @Id
    @Column(name = "idWypozyczenie")
    public int getIdWypozyczenie() {
        return idWypozyczenie;
    }

    public void setIdWypozyczenie(int idWypozyczenie) {
        this.idWypozyczenie = idWypozyczenie;
    }

    @Basic
    @Column(name = "DataWypozyczenia")
    public Date getDataWypozyczenia() {
        return dataWypozyczenia;
    }

    public void setDataWypozyczenia(Date dataWypozyczenia) {
        this.dataWypozyczenia = dataWypozyczenia;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        WypozyczenieEntity that = (WypozyczenieEntity) o;

        if (idWypozyczenie != that.idWypozyczenie) return false;
        if (dataWypozyczenia != null ? !dataWypozyczenia.equals(that.dataWypozyczenia) : that.dataWypozyczenia != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = idWypozyczenie;
        result = 31 * result + (dataWypozyczenia != null ? dataWypozyczenia.hashCode() : 0);
        return result;
    }
}
