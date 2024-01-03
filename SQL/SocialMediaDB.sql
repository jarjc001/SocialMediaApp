DROP DATABASE IF EXISTS socialmedia;
CREATE DATABASE socialmedia;
USE socialmedia;

-- CREATE primary tables for database

-- account table
DROP TABLE IF EXISTS userAccounts;
CREATE TABLE userAccounts(
	username VARCHAR(25) NOT NULL,		-- pk
    password VARCHAR(25) NOT NULL,
    email VARCHAR(50) NOT NULL,
    fullname VARCHAR(50) NOT NULL,
    
    CONSTRAINT pk_userAccounts
    PRIMARY KEY (username)
);

-- Creates Related Tables of the data Base

-- Posts table
-- one to many from users Accounts to this
DROP TABLE IF EXISTS posts;
CREATE TABLE posts(
	postID  BIGINT auto_increment,     -- pk
    username VARCHAR(25) NOT NULL,		-- fk
    content VARCHAR(280) NOT NULL,
    timePost TIMESTAMP NOT NULL,
    
    CONSTRAINT pk_posts
    PRIMARY KEY (postID),
    
    CONSTRAINT fk_posts_userAccounts
    FOREIGN KEY  (username)
    REFERENCES userAccounts(username)
);

-- comments table
-- one to many from posts and username to this
DROP TABLE IF EXISTS comments;
CREATE TABLE comments(
	commentID BIGINT auto_increment,   -- pk
	postID BIGINT NOT NULL,			-- fk
    username VARCHAR(25) NOT NULL,  -- fk username of the commenter
    commentContent VARCHAR(280) NOT NULL,
    timeComment TIMESTAMP NOT NULL,
    
	CONSTRAINT pk_comments
    PRIMARY KEY (commentID),
    
    CONSTRAINT fk_comments_posts
    FOREIGN KEY  (postID)
    REFERENCES posts(postID),
    
    CONSTRAINT fk_comments_userAccounts
    FOREIGN KEY  (username)
    REFERENCES userAccounts(username)
);


-- likes table
-- many to many from posts to users
-- a user can only like a post once
DROP TABLE IF EXISTS likes;
CREATE TABLE likes(
	postID BIGINT NOT NULL,			-- fk
	username VARCHAR(25) NOT NULL,  -- fk username of the liker
    
    CONSTRAINT pk_likes				
    PRIMARY KEY (postID, username),
    
    CONSTRAINT fk_likes_userAccounts
    FOREIGN KEY  (username)
    REFERENCES userAccounts(username),
    
    CONSTRAINT fk_likes_posts
    FOREIGN KEY  (postID)
    REFERENCES posts(postID)
    );
    
    
-- subscriptions table
-- one to many with current user and other users

Drop TABLE IF EXISTS subscriptions;
CREATE TABLE subscriptions(
	username VARCHAR(25) NOT NULL,	
    subUsers VARCHAR(25) NOT NULL,	-- other users that this user is subscribed to 
    
    CONSTRAINT pk_subscriptions		
    PRIMARY KEY (username, subUsers),
    
    CONSTRAINT fk_subscriptions_userAccounts
    FOREIGN KEY  (username)
    REFERENCES userAccounts(username),
    
    -- need ffk
    CONSTRAINT ffk_subscriptions_userAccounts
    FOREIGN KEY  (subUsers)
    REFERENCES userAccounts(username)


);


show tables;

