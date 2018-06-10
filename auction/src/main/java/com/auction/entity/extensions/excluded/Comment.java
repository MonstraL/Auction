package com.auction.entity.extensions.excluded;

import java.sql.Timestamp;

/*
* by DA
* 11/22/17
* */


public class Comment {

    private int commentId;
    private int goodsId;
    private int userId;
    private String content;
    private Timestamp createdTime;

    public int getCommentId() {
        return commentId;
    }

    public void setCommentId(int commentId) {
        this.commentId = commentId;
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

        Comment comment = (Comment) o;

        if (commentId != comment.commentId) return false;
        if (goodsId != comment.goodsId) return false;
        if (userId != comment.userId) return false;
        if (content != null ? !content.equals(comment.content) : comment.content != null) return false;
        return createdTime != null ? createdTime.equals(comment.createdTime) : comment.createdTime == null;
    }

    @Override
    public int hashCode() {
        int result = commentId;
        result = 31 * result + goodsId;
        result = 31 * result + userId;
        result = 31 * result + (content != null ? content.hashCode() : 0);
        result = 31 * result + (createdTime != null ? createdTime.hashCode() : 0);
        return result;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentId=" + commentId +
                ", goodsId=" + goodsId +
                ", userId=" + userId +
                ", content='" + content + '\'' +
                ", createdTime=" + createdTime +
                '}';
    }
}
