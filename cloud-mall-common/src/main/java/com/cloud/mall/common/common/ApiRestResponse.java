package com.cloud.mall.common.common;

import com.cloud.mall.common.exception.MallExceptionEnum;

public class ApiRestResponse<T> {
    private Integer status;
    private String msg;
    private T data;
    private static final int OK_CODE = 10000;
    private static final String OK_MESSAGE = "SUCCESS";

    public ApiRestResponse(Integer status, String msg, T data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public ApiRestResponse(Integer status, String msg) {
        this.status = status;
        this.msg = msg;
    }

    public ApiRestResponse() {
        this(OK_CODE, OK_MESSAGE);
    }

    public static <T> ApiRestResponse success() {
        return new ApiRestResponse(OK_CODE, OK_MESSAGE);
    }

    public static <T> ApiRestResponse success(T data) {
        ApiRestResponse response = new ApiRestResponse();
        response.setData(data);
        return response;
    }

    public static <T> ApiRestResponse error(Integer code, String msg) {
        return new ApiRestResponse<>(code, msg);
    }

    public static <T> ApiRestResponse error(MallExceptionEnum PostExceptionEnum) {
        return new ApiRestResponse(PostExceptionEnum.getCode(), PostExceptionEnum.getMsg());
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }

    public static int getOkCode() {
        return OK_CODE;
    }

    public static String getOkMessage() {
        return OK_MESSAGE;
    }

    @Override
    public String toString() {
        return "ApiRestResponse{" +
                "status=" + status +
                ", msg='" + msg + '\'' +
                ", data=" + data +
                '}';
    }
}
