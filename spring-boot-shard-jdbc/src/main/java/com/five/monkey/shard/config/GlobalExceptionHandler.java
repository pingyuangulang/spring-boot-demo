package com.five.monkey.shard.config;

import com.five.monkey.common.exception.BaseException;
import com.five.monkey.common.exception.ErrorCode;
import com.five.monkey.common.vo.base.BaseSingleResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author jim
 * @date 2020/10/28 15:46
 */
@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandler {

    @ExceptionHandler(BaseException.class)
    public BaseSingleResult baseExceptionHandler(BaseException e) {
        log.error("BaseException", e);
        return new BaseSingleResult<>(e.getCode(), e.getMessage(), null);
    }

    @ExceptionHandler(MethodArgumentNotValidException.class)
    public BaseSingleResult methodArgumentNotValidExceptionHandler(MethodArgumentNotValidException e) {
        log.error("MethodArgumentNotValidException", e);
        String message = e.getBindingResult().getAllErrors().get(0).getDefaultMessage();
        return new BaseSingleResult<>(ErrorCode._1000.ILLEGAL_ARGUMENT, message, null);
    }

    @ExceptionHandler(Exception.class)
    public BaseSingleResult exceptionHandler(Exception e) {
        log.error("global exception", e);
        return new BaseSingleResult<>(500, e.toString(), null);
    }
}
