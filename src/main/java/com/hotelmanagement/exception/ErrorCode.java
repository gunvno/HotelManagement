package com.hotelmanagement.exception;

import lombok.Data;
import lombok.Getter;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;

@Getter
public enum ErrorCode {
    // System
    // ===== LỖI HỆ THỐNG (9xxx) =====
    UNCATEGORIZED_EXCEPTION(9999, "Lỗi không xác định", HttpStatus.INTERNAL_SERVER_ERROR),
    INVALID_KEY(9001, "Khóa thông báo không hợp lệ", HttpStatus.BAD_REQUEST),

    // Users
    USER_IS_EXISTED( 1001, "Nguời dùng đã tồn tại", HttpStatus.BAD_REQUEST),
    USERNAME_INVALID(1002,"Tên người dùng phải ít nhất {min} kí tự", HttpStatus.BAD_REQUEST),
    PASSWORD_INVALID(1003,"Mật khẩu phải ít nhất {min} kí tự", HttpStatus.BAD_REQUEST),
    USER_NOT_FOUND(1004, "Người dùng không tồn tại", HttpStatus.NOT_FOUND),
    DOB_INVALID(1005,"Your age must be at least {min} ",HttpStatus.BAD_REQUEST),
    USER_ALREADY_UNACTIVE(1006,"User is already unactive",HttpStatus.BAD_REQUEST),
    USER_ALREADY_ACTIVE(1007,"User is already active",HttpStatus.BAD_REQUEST),

    //Authen
    UNAUTHENTICATED(1101,"Unauthenticated",HttpStatus.UNAUTHORIZED),
    UNAUTHORIZED(1102,"You do not have permission",HttpStatus.FORBIDDEN),

    //register
    USERNAME_NOT_NULL(1201,"Username is empty", HttpStatus.BAD_REQUEST),
    PASSWORD_NOT_NULL(1202,"Password is empty", HttpStatus.BAD_REQUEST),

    //Permission
    PERMISSION_IS_EXISTED(1301,"Permission is existed",HttpStatus.BAD_REQUEST)
    ;


    ErrorCode(int code, String message, HttpStatusCode statusCode) {
        this.code = code;
        this.message = message;
        this.statusCode = statusCode;
    }

    private int code;
    private String message;
    private HttpStatusCode statusCode;

}
