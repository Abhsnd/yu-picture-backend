package com.yupi.yupicturebackend.exception;

/*
 * @Author winku
 * @Date 2025/2/2 1:17
 * @Description 异常处理工具类
 * @Since version-1.0
 */
public class ThrowUtils {

    /*
     * @Author winku
     * @Date 2025/2/2 1:13
     * @Description 条件成立则抛异常
     * @Param [condition 条件, runtimeException 异常]
     * @Return void
     * @Since version-1.0
     */
    public static void throwIf(boolean condition, RuntimeException runtimeException) {
        if (condition) {
            throw runtimeException;
        }
    }

    /*
     * @Author winku
     * @Date 2025/2/2 1:16
     * @Description 条件成立则抛异常
     * @Param [condition 条件, errorCode 错误码]
     * @Return void
     * @Since version-1.0
     */
    public static void throwIf(boolean condition, ErrorCode errorCode) {
        throwIf(condition, new BusinessException(errorCode));
    }

    /*
     * @Author winku
     * @Date 2025/2/2 1:16
     * @Description 条件成立则抛异常
     * @Param [condition 条件, errorCode 错误码, message 错误信息]
     * @Return void
     * @Since version-1.0
     */
    public static void throwIf(boolean condition, ErrorCode errorCode, String message) {
        throwIf(condition, new BusinessException(errorCode, message));
    }
}
