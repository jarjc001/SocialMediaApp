package com.TwitterCopy.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class Post {

    private long postID;
    private String username;
    private String content;
    private Timestamp timePost;

    public Post(long postID, String username, String content, Timestamp timePost) {
        this.postID = postID;
        this.username = username;
        this.content = content;
        this.timePost = timePost;
    }

    public Post(String username, String content, Timestamp timePost) {
        this.username = username;
        this.content = content;
        this.timePost = timePost;
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
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", timePost=" + timePost +
                '}';
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postID == post.postID && Objects.equals(username, post.username) && Objects.equals(content, post.content) && Objects.equals(timePost, post.timePost);
    }

    @Override
    public int hashCode() {
        return Objects.hash(postID, username, content, timePost);
    }
}
