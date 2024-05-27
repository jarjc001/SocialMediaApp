package com.socialmediaapp.socialmediaapppost.dto;

import java.sql.Timestamp;
import java.util.Objects;

public class Post {

    //todo
    // may change user object to just username

    private long postId;
    private User user;
    private String username;
    private String content;
    private Timestamp timePost;

    public Post(long postId, User user, String content, Timestamp timePost) {
        this.postId = postId;
        this.user = user;
        this.content = content;
        this.timePost = timePost;
    }

    /**
     * Use this one for creating new posts into the db
     * @param user user
     * @param content content
     * @param timePost time post
     */
    public Post(User user, String content, Timestamp timePost) {
        this.user = user;
        this.content = content;
        this.timePost = timePost;
    }

    public Post(long postId, String username, String content, Timestamp timePost) {
        this.postId = postId;
        this.username = username;
        this.content = content;
        this.timePost = timePost;
    }

    public Post(String username, String content, Timestamp timePost) {
        this.username = username;
        this.content = content;
        this.timePost = timePost;
    }



    public long getPostId() {
        return postId;
    }

    public void setPostId(long postId) {
        this.postId = postId;
    }

    public User getUser() {
        return user;
    }

    public void setUser(User user) {
        this.user = user;
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
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Post post = (Post) o;
        return postId == post.postId && Objects.equals(username, post.username) && Objects.equals(content, post.content) && Objects.equals(timePost, post.timePost);
    }

    @Override
    public String toString() {
        return "Post{" +
                "postId=" + postId +
                ", username='" + username + '\'' +
                ", content='" + content + '\'' +
                ", timePost=" + timePost +
                '}';
    }

    @Override
    public int hashCode() {
        return Objects.hash(postId, username, content, timePost);
    }
}
