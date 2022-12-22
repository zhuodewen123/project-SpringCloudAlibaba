package com.zdw.inbound.config.exception;

import cn.dev33.satoken.util.SaResult;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * Sa-Token全局异常拦截
 */
@RestControllerAdvice
public class GlobalExceptionHandler {

    /**
     * 全局异常拦截
     * @param e
     * @return
     */
    @ExceptionHandler
    public SaResult handlerException(Exception e) {
        e.printStackTrace();
        return SaResult.error(e.getMessage());
    }
}
