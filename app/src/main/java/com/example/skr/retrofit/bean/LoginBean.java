package com.example.skr.retrofit.bean;

public class LoginBean {

    /**
     * message : 登陆失败,手机号或密码错误
     * status : 1001
     */

    private String message;
    private String status;

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
