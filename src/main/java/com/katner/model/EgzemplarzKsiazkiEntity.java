package com.katner.model;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

/**
 * Created by michal on 25.10.15.
 */
@Entity
@Table(name = "EgzemplarzKsiazki", schema = "", catalog = "mydb")
public class EgzemplarzKsiazkiEntity {
    private int idEgzemplarz;

    @Id
    @Column(name = "idEgzemplarz")
    public int getIdEgzemplarz() {
        return idEgzemplarz;
    }

    public void setIdEgzemplarz(int idEgzemplarz) {
        this.idEgzemplarz = idEgzemplarz;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        EgzemplarzKsiazkiEntity that = (EgzemplarzKsiazkiEntity) o;

        if (idEgzemplarz != that.idEgzemplarz) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return idEgzemplarz;
    }
}
