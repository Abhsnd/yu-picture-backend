package com.yupi.yupicturebackend.exception;

import lombok.Getter;

/*
 * @Author winku
 * @Date 2025/2/2 1:11
 * @Description 业务异常类
 * @Since version-1.0
 */

@Getter
public class BusinessException extends RuntimeException{

    /**
     * 错误码
     */
    private final int code;

    public BusinessException(int code, String message) {
        super(message);
        this.code = code;
    }

    public BusinessException(ErrorCode errorCode) {
        super(errorCode.getMessage());
        this.code = errorCode.getCode();
    }

    public BusinessException(ErrorCode errorCode, String message) {
        super(message);
        this.code = errorCode.getCode();
    }
}
