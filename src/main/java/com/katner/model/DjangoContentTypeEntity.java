package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "django_content_type", schema = "wypozyczalnia", catalog = "")
public class DjangoContentTypeEntity {
    private int id;
    private String appLabel;
    private String model;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "app_label", nullable = false, length = 100)
    public String getAppLabel() {
        return appLabel;
    }

    public void setAppLabel(String appLabel) {
        this.appLabel = appLabel;
    }

    @Basic
    @Column(name = "model", nullable = false, length = 100)
    public String getModel() {
        return model;
    }

    public void setModel(String model) {
        this.model = model;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DjangoContentTypeEntity that = (DjangoContentTypeEntity) o;

        if (id != that.id) return false;
        if (appLabel != null ? !appLabel.equals(that.appLabel) : that.appLabel != null) return false;
        if (model != null ? !model.equals(that.model) : that.model != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (appLabel != null ? appLabel.hashCode() : 0);
        result = 31 * result + (model != null ? model.hashCode() : 0);
        return result;
    }
}
