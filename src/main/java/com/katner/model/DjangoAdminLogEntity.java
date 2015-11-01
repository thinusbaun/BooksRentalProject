package com.katner.model;

import javax.persistence.*;
import java.sql.Timestamp;

/**
 * Created by michal on 01.11.15.
 */
@Entity
@Table(name = "django_admin_log", schema = "", catalog = "wypozyczalnia")
public class DjangoAdminLogEntity {
    private int id;
    private Timestamp actionTime;
    private String objectId;
    private String objectRepr;
    private short actionFlag;
    private String changeMessage;
    private Integer contentTypeId;
    private int userId;

    @Id
    @Column(name = "id")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "action_time")
    public Timestamp getActionTime() {
        return actionTime;
    }

    public void setActionTime(Timestamp actionTime) {
        this.actionTime = actionTime;
    }

    @Basic
    @Column(name = "object_id")
    public String getObjectId() {
        return objectId;
    }

    public void setObjectId(String objectId) {
        this.objectId = objectId;
    }

    @Basic
    @Column(name = "object_repr")
    public String getObjectRepr() {
        return objectRepr;
    }

    public void setObjectRepr(String objectRepr) {
        this.objectRepr = objectRepr;
    }

    @Basic
    @Column(name = "action_flag")
    public short getActionFlag() {
        return actionFlag;
    }

    public void setActionFlag(short actionFlag) {
        this.actionFlag = actionFlag;
    }

    @Basic
    @Column(name = "change_message")
    public String getChangeMessage() {
        return changeMessage;
    }

    public void setChangeMessage(String changeMessage) {
        this.changeMessage = changeMessage;
    }

    @Basic
    @Column(name = "content_type_id")
    public Integer getContentTypeId() {
        return contentTypeId;
    }

    public void setContentTypeId(Integer contentTypeId) {
        this.contentTypeId = contentTypeId;
    }

    @Basic
    @Column(name = "user_id")
    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DjangoAdminLogEntity that = (DjangoAdminLogEntity) o;

        if (id != that.id) return false;
        if (actionFlag != that.actionFlag) return false;
        if (userId != that.userId) return false;
        if (actionTime != null ? !actionTime.equals(that.actionTime) : that.actionTime != null) return false;
        if (objectId != null ? !objectId.equals(that.objectId) : that.objectId != null) return false;
        if (objectRepr != null ? !objectRepr.equals(that.objectRepr) : that.objectRepr != null) return false;
        if (changeMessage != null ? !changeMessage.equals(that.changeMessage) : that.changeMessage != null)
            return false;
        if (contentTypeId != null ? !contentTypeId.equals(that.contentTypeId) : that.contentTypeId != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (actionTime != null ? actionTime.hashCode() : 0);
        result = 31 * result + (objectId != null ? objectId.hashCode() : 0);
        result = 31 * result + (objectRepr != null ? objectRepr.hashCode() : 0);
        result = 31 * result + (int) actionFlag;
        result = 31 * result + (changeMessage != null ? changeMessage.hashCode() : 0);
        result = 31 * result + (contentTypeId != null ? contentTypeId.hashCode() : 0);
        result = 31 * result + userId;
        return result;
    }
}
