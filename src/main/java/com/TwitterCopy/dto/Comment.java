package com.TwitterCopy.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class Comment {

    private long commentID;
    private long postID;
    private String username;
    private String commentContent;
    private Timestamp timePost;


    public Comment(long commentID, long postID, String username, String commentContent, Timestamp timePost) {
        this.commentID = commentID;
        this.postID = postID;
        this.username = username;
        this.commentContent = commentContent;
        this.timePost = timePost;
    }

    public Comment(long postID, String username, String commentContent, Timestamp timePost) {
        this.postID = postID;
        this.username = username;
        this.commentContent = commentContent;
        this.timePost = timePost;
    }

    public long getCommentID() {
        return commentID;
    }

    public void setCommentID(long commentID) {
        this.commentID = commentID;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getCommentContent() {
        return commentContent;
    }

    public void setCommentContent(String commentContent) {
        this.commentContent = commentContent;
    }

    public Timestamp getTimePost() {
        return timePost;
    }

    public void setTimePost(Timestamp timePost) {
        this.timePost = timePost;
    }

    @Override
    public String toString() {
        return "Comment{" +
                "commentID=" + commentID +
                ", postID=" + postID +
                ", username='" + username + '\'' +
                ", commentContent='" + commentContent + '\'' +
                ", timePost=" + timePost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Comment comment = (Comment) o;
        return commentID == comment.commentID && postID == comment.postID && Objects.equals(username, comment.username) && Objects.equals(commentContent, comment.commentContent) && Objects.equals(timePost, comment.timePost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(commentID, postID, username, commentContent, timePost);
    }
}
