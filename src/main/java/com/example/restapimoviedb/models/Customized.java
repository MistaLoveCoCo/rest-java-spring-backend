package com.example.restapimoviedb.models;

import java.util.List;

public class Customized<T> {

    private String message;
    private List<T> body;

    public Customized(String message, List<T> body) {
        this.message = message;
        this.body = body;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<T> getBody() {
        return body;
    }

    public void setBody(List<T> body) {
        this.body = body;
    }
}
