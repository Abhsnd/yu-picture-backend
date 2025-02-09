package com.yupi.yupicturebackend.exception;

import com.yupi.yupicturebackend.common.BaseResponse;
import com.yupi.yupicturebackend.common.ResultUtils;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/*
 * @Author winku
 * @Date 2025/2/2 1:29
 * @Description 全局异常处理器
 * @Since version-1.0
 */

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {
    /*
     * @Author winku
     * @Date 2025/2/2 1:40
     * @Description 捕获业务异常
     * @Param [e]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<?>
     * @Since version-1.0
     */
    @ExceptionHandler(BusinessException.class)
    public BaseResponse<?> businessExceptionHandler(BusinessException e) {
        log.error(e.getMessage());
        return ResultUtils.error(e.getCode(), e.getMessage());
    }

    /*
     * @Author winku
     * @Date 2025/2/2 1:40
     * @Description 捕获运行异常
     * @Param [e]
     * @Return com.yupi.yupicturebackend.common.BaseResponse<?>
     * @Since version-1.0
     */
    @ExceptionHandler(RuntimeException.class)
    public BaseResponse<?> runtimeExceptionHandler(RuntimeException e) {
        log.error("RuntimeException", e);
        return ResultUtils.error(ErrorCode.SYSTEM_ERROR, "系统错误");
    }
}
