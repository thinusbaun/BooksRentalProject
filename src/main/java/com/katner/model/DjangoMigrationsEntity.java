package com.katner.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "django_migrations", schema = "wypozyczalnia", catalog = "")
public class DjangoMigrationsEntity {
    private int id;
    private String app;
    private String name;
    private Timestamp applied;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "app", nullable = false, length = 255)
    public String getApp() {
        return app;
    }

    public void setApp(String app) {
        this.app = app;
    }

    @Basic
    @Column(name = "name", nullable = false, length = 255)
    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    @Basic
    @Column(name = "applied", nullable = false)
    public Timestamp getApplied() {
        return applied;
    }

    public void setApplied(Timestamp applied) {
        this.applied = applied;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DjangoMigrationsEntity that = (DjangoMigrationsEntity) o;

        if (id != that.id) return false;
        if (app != null ? !app.equals(that.app) : that.app != null) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (applied != null ? !applied.equals(that.applied) : that.applied != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (app != null ? app.hashCode() : 0);
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + (applied != null ? applied.hashCode() : 0);
        return result;
    }
}
