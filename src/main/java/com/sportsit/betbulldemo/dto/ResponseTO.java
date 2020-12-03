package com.sportsit.betbulldemo.dto;

import org.springframework.stereotype.Component;

@Component
public class ResponseTO {

    private String description;
    private Integer statusCode;
    private String data;
    private boolean Status;

    public String getDescription(String reasonPhrase) {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public Integer getStatusCode(int value) {
        return statusCode;
    }

    public void setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
    }

    public String getData() {
        return data;
    }

    public void setData(String data) {
        this.data = data;
    }

    public boolean isStatus() {
        return Status;
    }

    public void setStatus(boolean status) {
        Status = status;
    }
}
