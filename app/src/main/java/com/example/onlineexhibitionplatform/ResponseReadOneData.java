package com.example.onlineexhibitionplatform;

import com.google.gson.annotations.SerializedName;

public class ResponseReadOneData {
    int status;
    boolean success;
    String message;
    @SerializedName("data") Data databody;

    public ResponseReadOneData(int status, boolean success, String message, Data databody) {
        this.status = status;
        this.success = success;
        this.message = message;
        this.databody = databody;
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

    public Data getDatabody() {
        return databody;
    }

    public void setDatabody(Data databody) {
        this.databody = databody;
    }

    public class Data {
        /*
        res = {id, title, author, content, updated_at}
         */
        String id;
        String title;
        String author;
        String content;
        String created_at;
        String updated_at;
        String deleted;

        public Data(String id, String title, String author, String content, String created_at, String updated_at, String deleted) {
            this.id = id;
            this.title = title;
            this.author = author;
            this.content = content;
            this.created_at = created_at;
            this.updated_at = updated_at;
            this.deleted = deleted;
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

        public String getContent() {
            return content;
        }

        public void setContent(String content) {
            this.content = content;
        }

        public String getCreated_at() {
            return created_at;
        }

        public void setCreated_at(String created_at) {
            this.created_at = created_at;
        }

        public String getUpdated_at() {
            return updated_at;
        }

        public void setUpdated_at(String updated_at) {
            this.updated_at = updated_at;
        }

        public String getDeleted() {
            return deleted;
        }

        public void setDeleted(String deleted) {
            this.deleted = deleted;
        }
    }



}
