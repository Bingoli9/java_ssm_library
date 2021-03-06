package com.mecol.bookshop_ssm.entity;

import java.io.Serializable;

public class ReaderCard implements Serializable {

    private long reader_id;
    private String username;
    private String password;

    public long getReaderId() {
        return reader_id;
    }

    public void setReaderId(long reader_id) {
        this.reader_id = reader_id;
    }

    public String getName() {
        return username;
    }

    public void setName(String username) {
        this.username = username;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    @Override
    public String toString() {
        return "ReaderCard{" +
                "reader_id=" + reader_id +
                ", username='" + username + '\'' +
                ", password='" + password + '\'' +
                '}';
    }
}
