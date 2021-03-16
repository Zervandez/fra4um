package com.k0k0.zervandez.forum;

public class Post {

    private String postText;

    public Post() {};

    public Post(String pT) {
        this.postText = pT;
    }

    public String getPostText() {
        return this.postText;
    }

    public void setPostText(String postText) {
        this.postText = postText;
    }



}
