package com.socialmediaapp.socialmediaapppost.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class Post {

    private long postID;
    private User user;
    private String content;
    private Timestamp timePost;

    public Post(long postID, User user, String content, Timestamp timePost) {
        this.postID = postID;
        this.user = user;
        this.content = content;
        this.timePost = timePost;
    }

    public Post(User user, String content, Timestamp timePost) {
        this.user = user;
        this.content = content;
        this.timePost = timePost;
    }

    public long getPostID() {
        return postID;
    }

    public void setPostID(long postID) {
        this.postID = postID;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }

    public Timestamp getTimePost() {
        return timePost;
    }

    public void setTimePost(Timestamp timePost) {
        this.timePost = timePost;
    }

    @Override
    public String toString() {
        return "Post{" +
                "postID=" + postID +
                ", username='" + user + '\'' +
                ", content='" + content + '\'' +
                ", timePost=" + timePost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postID == post.postID && Objects.equals(user, post.user) && Objects.equals(content, post.content) && Objects.equals(timePost, post.timePost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postID, user, content, timePost);
    }
}
