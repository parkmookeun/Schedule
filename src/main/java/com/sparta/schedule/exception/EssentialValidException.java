package com.sparta.schedule.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class EssentialValidException {
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> commonExceptionHandler(MethodArgumentNotValidException e){
        HttpHeaders httpHeaders = new HttpHeaders();
       Map<String, String> map = new HashMap<>();
        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;
        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", String.valueOf(httpStatus.value()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map,httpHeaders, httpStatus);
    }
}
