package com.example.foucson.db;

import java.util.Date;

public class Information {
    private Date time;
    private String content;


    public Date getTime() {
        return time;
    }

    public void setTime(Date time) {
        this.time = time;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content;
    }
}
