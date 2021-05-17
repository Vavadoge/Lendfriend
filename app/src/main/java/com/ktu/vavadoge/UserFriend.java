package com.ktu.vavadoge;

public class UserFriend {
    private  String username;
    private  String status;
    private  String type;

    public UserFriend(String username, String status, String type)
    {
        this.username = username;
        this.status = status;
        this.type = type;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }
    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }






}
