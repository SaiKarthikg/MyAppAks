package com.ebrd.insurance.dto;

public class ResponseDto<T> {
    private String status;
    private T data;
    private String message;

    public ResponseDto() {
    }

    public ResponseDto(String status, T data, String message) {
        this.status = status;
        this.data = data;
        this.message = message;
    }

    public String getStatus() {
        return status;
    }


    public T getData() {
        return data;
    }

    public String getMessage() {
        return message;
    }

    public static <T> ResponseDto<T> asFailure(String message) {
        return new ResponseDto<T>("FAILURE", null, message);
    }

    public static <T> ResponseDto<T> asSuccess(T data) {
        return new ResponseDto<T>("SUCCESS", data, null);
    }
}