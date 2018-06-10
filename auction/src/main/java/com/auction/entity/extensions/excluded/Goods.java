package com.auction.entity.extensions.excluded;

import java.sql.Timestamp;

/*
* by DA
* 11/22/17
* */


public class Goods{

    private int goodsId;
    //@OneToOne
    private int userId;
    private String title;
    private String content;
    private int price;
    private Timestamp deadline; // TIME
    private String picture;
    private String status;
    private Timestamp createdTime; // TIME

    public String getPicture() {
        return picture;
    }

    public void setPicture(String picture) {
        this.picture = picture;
    }

    public int getGoodsId() {
        return goodsId;
    }

    public void setGoodsId(int goodsId) {
        this.goodsId = goodsId;
    }

    public int getUserId() {
        return userId;
    }

    public void setUserId(int userId) {
        this.userId = userId;
    }

    public String getTitle() {
        return title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public Timestamp getDeadline() {
        return deadline;
    }

    public void setDeadline(Timestamp deadline) {
        this.deadline = deadline;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
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

        Goods goods = (Goods) o;

        if (goodsId != goods.goodsId) return false;
        if (userId != goods.userId) return false;
        if (price != goods.price) return false;
        if (title != null ? !title.equals(goods.title) : goods.title != null) return false;
        if (content != null ? !content.equals(goods.content) : goods.content != null) return false;
        if (deadline != null ? !deadline.equals(goods.deadline) : goods.deadline != null) return false;
        if (picture != null ? !picture.equals(goods.picture) : goods.picture != null) return false;
        if (status != null ? !status.equals(goods.status) : goods.status != null) return false;
        return createdTime != null ? createdTime.equals(goods.createdTime) : goods.createdTime == null;
    }

    @Override
    public int hashCode() {
        int result = goodsId;
        result = 31 * result + userId;
        result = 31 * result + (title != null ? title.hashCode() : 0);
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + price;
        result = 31 * result + (deadline != null ? deadline.hashCode() : 0);
        result = 31 * result + (picture != null ? picture.hashCode() : 0);
        result = 31 * result + (status != null ? status.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Goods{" +
                "goodsId=" + goodsId +
                ", userId=" + userId +
                ", title='" + title + '\'' +
                ", content='" + content + '\'' +
                ", price=" + price +
                ", deadline=" + deadline +
                ", picture='" + picture + '\'' +
                ", status='" + status + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
