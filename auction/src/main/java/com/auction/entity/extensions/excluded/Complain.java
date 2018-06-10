package com.auction.entity.extensions.excluded;


import java.sql.Timestamp;


public class Complain {

    private int complainId;
    private int complainerId;
    private String content;
    private Timestamp createdTime;

    public int getComplainId() {
        return complainId;
    }

    public void setComplainId(int complainId) {
        this.complainId = complainId;
    }

    public int getComplainerId() {
        return complainerId;
    }

    public void setComplainerId(int complainerId) {
        this.complainerId = complainerId;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getCreatedTime() {
        return createdTime;
    }

    public void setCreatedTime(Timestamp createdTime) {
        this.createdTime = createdTime;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        Complain complain = (Complain) o;

        if (complainId != complain.complainId) return false;
        if (complainerId != complain.complainerId) return false;
        if (content != null ? !content.equals(complain.content) : complain.content != null) return false;
        return createdTime != null ? createdTime.equals(complain.createdTime) : complain.createdTime == null;
    }

    @Override
    public int hashCode() {
        int result = complainId;
        result = 31 * result + complainerId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }
}
