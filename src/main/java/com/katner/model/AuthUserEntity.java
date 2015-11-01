package com.katner.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by michal on 01.11.15.
 */
@Entity
@Table(name = "auth_user", schema = "", catalog = "wypozyczalnia")
public class AuthUserEntity {
    private int id;
    private String password;
    private Timestamp lastLogin;
    private byte isSuperuser;
    private String username;
    private String firstName;
    private String lastName;
    private String email;
    private byte isStaff;
    private byte isActive;
    private Timestamp dateJoined;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "password")
    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Basic
    @Column(name = "last_login")
    public Timestamp getLastLogin() {
        return lastLogin;
    }

    public void setLastLogin(Timestamp lastLogin) {
        this.lastLogin = lastLogin;
    }

    @Basic
    @Column(name = "is_superuser")
    public byte getIsSuperuser() {
        return isSuperuser;
    }

    public void setIsSuperuser(byte isSuperuser) {
        this.isSuperuser = isSuperuser;
    }

    @Basic
    @Column(name = "username")
    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    @Basic
    @Column(name = "first_name")
    public String getFirstName() {
        return firstName;
    }

    public void setFirstName(String firstName) {
        this.firstName = firstName;
    }

    @Basic
    @Column(name = "last_name")
    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    @Basic
    @Column(name = "email")
    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    @Basic
    @Column(name = "is_staff")
    public byte getIsStaff() {
        return isStaff;
    }

    public void setIsStaff(byte isStaff) {
        this.isStaff = isStaff;
    }

    @Basic
    @Column(name = "is_active")
    public byte getIsActive() {
        return isActive;
    }

    public void setIsActive(byte isActive) {
        this.isActive = isActive;
    }

    @Basic
    @Column(name = "date_joined")
    public Timestamp getDateJoined() {
        return dateJoined;
    }

    public void setDateJoined(Timestamp dateJoined) {
        this.dateJoined = dateJoined;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        AuthUserEntity that = (AuthUserEntity) o;

        if (id != that.id) return false;
        if (isSuperuser != that.isSuperuser) return false;
        if (isStaff != that.isStaff) return false;
        if (isActive != that.isActive) return false;
        if (password != null ? !password.equals(that.password) : that.password != null) return false;
        if (lastLogin != null ? !lastLogin.equals(that.lastLogin) : that.lastLogin != null) return false;
        if (username != null ? !username.equals(that.username) : that.username != null) return false;
        if (firstName != null ? !firstName.equals(that.firstName) : that.firstName != null) return false;
        if (lastName != null ? !lastName.equals(that.lastName) : that.lastName != null) return false;
        if (email != null ? !email.equals(that.email) : that.email != null) return false;
        if (dateJoined != null ? !dateJoined.equals(that.dateJoined) : that.dateJoined != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (password != null ? password.hashCode() : 0);
        result = 31 * result + (lastLogin != null ? lastLogin.hashCode() : 0);
        result = 31 * result + (int) isSuperuser;
        result = 31 * result + (username != null ? username.hashCode() : 0);
        result = 31 * result + (firstName != null ? firstName.hashCode() : 0);
        result = 31 * result + (lastName != null ? lastName.hashCode() : 0);
        result = 31 * result + (email != null ? email.hashCode() : 0);
        result = 31 * result + (int) isStaff;
        result = 31 * result + (int) isActive;
        result = 31 * result + (dateJoined != null ? dateJoined.hashCode() : 0);
        return result;
    }
}
