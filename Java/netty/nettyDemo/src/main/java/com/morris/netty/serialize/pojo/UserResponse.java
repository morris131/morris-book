package com.morris.netty.serialize.pojo;

import java.io.Serializable;

public class UserResponse implements Serializable {

    private int code;

    private String message;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    @Override
    public String toString() {
        return "UserResponse{" +
                "code=" + code +
                ", message='" + message + '\'' +
                '}';
    }
}
