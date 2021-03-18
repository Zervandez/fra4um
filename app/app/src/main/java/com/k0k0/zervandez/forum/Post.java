package com.k0k0.zervandez.forum;

import java.sql.Timestamp;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.TimerTask;

public class Post {

    private String postText;
    private final Date dateofPost = new Date();

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

    public String getDate() {
        String pattern = "dd-MM-yyyy";
        SimpleDateFormat simpleDateFormat = new SimpleDateFormat(pattern);
        String date = simpleDateFormat.format(dateofPost);
        return date;
    }



}
