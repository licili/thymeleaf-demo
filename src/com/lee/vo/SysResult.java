package com.lee.vo;

/**
 * @Author: ljg
 * @Date: 2025/9/9 10:03 AM Tuesday
 * @Description:
 */
public class SysResult {
    private Boolean flag;
    private String message;
    private Object data;


    public SysResult() {
    }


    public SysResult(Boolean flag, String message, Object data) {
        this.flag = flag;
        this.message = message;
        this.data = data;
    }

    public static SysResult success() {
        return new SysResult(true, "业务调用成功", null);
    }
    public static SysResult success(Object data) {
        return new SysResult(true, "业务调用成功", data);
    }
    public static SysResult success(String message, Object data) {
        return new SysResult(true, message, data);
    }

    public static SysResult fail() {
        return new SysResult(false, "业务调用失败", null);
    }

    public Boolean getFlag() {
        return flag;
    }

    public void setFlag(Boolean flag) {
        this.flag = flag;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }
}
