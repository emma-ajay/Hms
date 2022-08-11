package com.project.Hms.DTO.Response;

import com.fasterxml.jackson.annotation.JsonIgnore;
import org.springframework.http.HttpStatus;

public class GenericResponse {
    private String code;

    @JsonIgnore
    private HttpStatus httpStatus;
    private String message;
    private Object data;
    private Object metadata;

    public GenericResponse(String code, HttpStatus httpStatus, String message, Object data, Object metadata) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
    }

    public GenericResponse(String code, HttpStatus httpStatus, String message, Object data) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
        this.data = data;
    }

    public GenericResponse(String code, String message, Object data, Object metadata) {
        this.code = code;
        this.message = message;
        this.data = data;
        this.metadata = metadata;
    }

    public GenericResponse(String code, String message, Object data) {
        this.code = code;
        this.message = message;
        this.data = data;
    }

    public GenericResponse(String code, HttpStatus httpStatus, String message) {
        this.code = code;
        this.httpStatus = httpStatus;
        this.message = message;
    }

    public GenericResponse() {
    }

    public GenericResponse(String code, String message) {
        this.code = code;
        this.message = message;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public HttpStatus getHttpStatus() {
        return httpStatus;
    }

    public void setHttpStatus(HttpStatus httpStatus) {
        this.httpStatus = httpStatus;
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

    public Object getMetadata() {
        return metadata;
    }

    public void setMetadata(Object metadata) {
        this.metadata = metadata;
    }

    @Override
    public String toString() {
        return "GenericResponse{" +
                "code='" + code + '\'' +
                ", httpStatus=" + httpStatus +
                ", message='" + message + '\'' +
                ", data=" + data +
                ", metadata='" + metadata + '\'' +
                '}';
    }
}
