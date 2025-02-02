package com.yupi.yupicturebackend.common;

import com.yupi.yupicturebackend.exception.ErrorCode;

/*
 * @Author winku
 * @Date 2025/2/2 1:27
 * @Description 调用结果工具类
 * @Since version-1.0
 */
public class ResultUtils {
    /*
     * @Author winku
     * @Date 2025/2/2 1:23
     * @Description
     * @Param [data 数据 , <T> 数据类型]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<T>
     * @Since version-1.0
     */
    public static <T> BaseResponse<T> success(T data) {
        return new BaseResponse<>(0, data, "ok");
    }

    /*
     * @Author winku
     * @Date 2025/2/2 1:27
     * @Description
     * @Param [errorCode 错误码]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<?>
     * @Since version-1.0
     */
    public static BaseResponse<?> error(ErrorCode errorCode) {
        return new BaseResponse<>(errorCode);
    }

    /*
     * @Author winku
     * @Date 2025/2/2 1:27
     * @Description
     * @Param [code 错误码, message 错误信息]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<?>
     * @Since version-1.0
     */
    public static BaseResponse<?> error(int code, String message) {
        return new BaseResponse<>(code, null, message);
    }

    /*
     * @Author winku
     * @Date 2025/2/2 1:39
     * @Description 
     * @Param [errorCode 错误码, message 错误信息]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<?>
     * @Since version-1.0
     */
    public static BaseResponse<?> error(ErrorCode errorCode, String message) {
        return new BaseResponse<>(errorCode.getCode(), null, message);
    }
}
