package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 09.11.15.
 */
@Entity
@Table(name = "auth_permission", schema = "wypozyczalnia", catalog = "")
public class AuthPermissionEntity {
    private int id;
    private String name;
    private int contentTypeId;
    private String codename;

    @Id
    @Column(name = "id", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "content_type_id", nullable = false)
    public int getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(int contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    @Basic
    @Column(name = "codename", nullable = false, length = 100)
    public String getCodename() {
        return codename;
    }

    public void setCodename(String codename) {
        this.codename = codename;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthPermissionEntity that = (AuthPermissionEntity) o;

        if (id != that.id) return false;
        if (contentTypeId != that.contentTypeId) return false;
        if (name != null ? !name.equals(that.name) : that.name != null) return false;
        if (codename != null ? !codename.equals(that.codename) : that.codename != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (name != null ? name.hashCode() : 0);
        result = 31 * result + contentTypeId;
        result = 31 * result + (codename != null ? codename.hashCode() : 0);
        return result;
    }
}
