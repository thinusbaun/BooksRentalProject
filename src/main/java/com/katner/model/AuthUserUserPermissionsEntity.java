package com.katner.model;

import javax.persistence.*;

/**
 * Created by michal on 01.11.15.
 */
@Entity
@Table(name = "auth_user_user_permissions", schema = "", catalog = "wypozyczalnia")
public class AuthUserUserPermissionsEntity {
    private int id;
    private int userId;
    private int permissionId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Basic
    @Column(name = "permission_id")
    public int getPermissionId() {
        return permissionId;
    }

    public void setPermissionId(int permissionId) {
        this.permissionId = permissionId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUserUserPermissionsEntity that = (AuthUserUserPermissionsEntity) o;

        if (id != that.id) return false;
        if (userId != that.userId) return false;
        if (permissionId != that.permissionId) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + userId;
        result = 31 * result + permissionId;
        return result;
    }
}
