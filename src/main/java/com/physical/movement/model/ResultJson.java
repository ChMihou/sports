package com.physical.movement.model;

public class ResultJson {
    private String msg;

    private boolean success;

    public ResultJson(String msg, boolean b) {
        this.msg = msg;
        this.success = b;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public boolean isSuccess() {
        return success;
    }

    public void setSuccess(boolean success) {
        this.success = success;
    }

    public static ResultJson success(String msg) {
        return new ResultJson(msg, true);
    }

    public static ResultJson error(String msg) {
        return new ResultJson(msg, false);
    }
}
