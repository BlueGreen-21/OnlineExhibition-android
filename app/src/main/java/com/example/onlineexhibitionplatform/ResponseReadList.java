package com.example.onlineexhibitionplatform;

import com.google.gson.annotations.SerializedName;

import java.util.ArrayList;
import java.util.List;

public class ResponseReadList {

    int status;
    boolean success;
    String message;
    @SerializedName("data") ArrayList<Data> databody;

    public ResponseReadList(int status, boolean success, String message, ArrayList<Data> body) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.databody = body;
    }

    public int getStatus() {
        return status;
    }

    public void setStatus(int status) {
        this.status = status;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<Data> getData() {
        return databody;
    }

    public void setData(ArrayList<Data> body) {
        this.databody = body;
    }


    public class Data {
        /*
    res = {id, title, author, content, updated_at}
     */
        String id;
        String title;
        String author;
        String updated_at;

        public Data(String id, String title, String author, String updated_at) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.updated_at = updated_at;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getTitle() {
            return title;
        }

        public void setTitle(String title) {
            this.title = title;
        }

        public String getAuthor() {
            return author;
        }

        public void setAuthor(String author) {
            this.author = author;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }
    }

}

