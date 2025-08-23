package com.jp.produtos.web.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.time.LocalDateTime;

import static org.springframework.http.HttpStatus.BAD_REQUEST;
import static org.springframework.http.HttpStatus.INTERNAL_SERVER_ERROR;

@RestControllerAdvice
public class ApiControllerAdvice {

    private final Logger logger = LoggerFactory.getLogger(ApiControllerAdvice.class);

    @ResponseBody
    @ResponseStatus(INTERNAL_SERVER_ERROR)
    @ExceptionHandler(Exception.class)
    public ErrorResponse handleException(HttpServletRequest req, Exception ex) {
        logger.error("Exception: {}", ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(),
                INTERNAL_SERVER_ERROR.value(),
                INTERNAL_SERVER_ERROR.getReasonPhrase(),
                req.getMethod(),
                req.getRequestURI(),
                ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(RuntimeException.class)
    public ErrorResponse handleRuntimeException(HttpServletRequest req, RuntimeException ex) {
        logger.error("RuntimeException: {}", ex.getMessage());
        return new ErrorResponse(LocalDateTime.now(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                req.getMethod(),
                req.getRequestURI(),
                ex.getMessage());
    }

    @ResponseBody
    @ResponseStatus(BAD_REQUEST)
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ErrorResponse handleBindingResultException(HttpServletRequest req, MethodArgumentNotValidException e, BindingResult result) {
        logger.info("{}", e.getMessage());
        String field = result.getFieldError().getField().toString();
        String message = result.getFieldError().getDefaultMessage();
        logger.error("Exception bindingResult: {}", field + " " + message);
        return new ErrorResponse(LocalDateTime.now(),
                BAD_REQUEST.value(),
                BAD_REQUEST.getReasonPhrase(),
                req.getMethod(),
                req.getRequestURI(),
                "field['" + field + "']: " + message);
    }
}
