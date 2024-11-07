package com.sparta.schedule.exception;

import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.server.ResponseStatusException;

import java.util.HashMap;
import java.util.Map;

/**
 * 예외 발생시, 공통으로 관리하는 클래스
 */
@RestControllerAdvice
public class EssentialValidException {

    /**
     *
     * @param e - Dto 클래스의 유효성 검사 실패시 발생하는 예외
     * @return 예외 발생시, {@code BAD_REQUEST(400)}과 에러타입, 코드, 메시지 반환
     */
    @ExceptionHandler(value = MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,String>> commonExceptionHandler(MethodArgumentNotValidException e) {

        HttpHeaders httpHeaders = new HttpHeaders();

       Map<String, String> map = new HashMap<>();

        HttpStatus httpStatus = HttpStatus.BAD_REQUEST;

        map.put("error type", httpStatus.getReasonPhrase());
        map.put("code", String.valueOf(httpStatus.value()));
        map.put("message", e.getMessage());

        return new ResponseEntity<>(map,httpHeaders, httpStatus);
    }

    /**
     *
     * @param e - 유저 아이디 조회시, 잘못된 아이디 조회로 인해 발생하는 예외
     * @return 예외 발생시, {@code NOT_FOUND(404)}과 에러타입, 코드, 메시지 반환
     */
    @ExceptionHandler(value = ResponseStatusException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(ResponseStatusException e){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus code = HttpStatus.NOT_FOUND;

        Map<String,String> map = new HashMap<>();
        map.put("error type", code.getReasonPhrase());
        map.put("code", String.valueOf(code.value()));
        map.put("message","아이디에 해당하는 일정이 없습니다!");

        return new ResponseEntity<>(map,httpHeaders, code);
    }

    /**
     *
     * @param e - 수정, 삭제 요청시 요청 패스워드와 DB에 저장된 패스워드가 달라서 발생하는 예외
     * @return 예외 발생시, {@code NOT_ACCEPTABLE(406)}과 에러타입, 코드, 메시지 반환
     */
    @ExceptionHandler(value = PasswordNotCorrectException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(PasswordNotCorrectException e){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus code = HttpStatus.NOT_ACCEPTABLE;

        Map<String,String> map = new HashMap<>();
        map.put("error type", code.getReasonPhrase());
        map.put("code", String.valueOf(code.value()));
        map.put("message",e.getMessage());

        return new ResponseEntity<>(map,httpHeaders, code);
    }

    /**
     *
     * @param e - RequestParam 필수값이 없을 때 발생하는 예외
     * @return 예외 발생시, {@code BAD_REQUEST(400)}과 에러타입, 코드, 메시지 반환
     */
    @ExceptionHandler(value = MissingServletRequestParameterException.class)
    public ResponseEntity<Map<String,String>> ExceptionHandler(MissingServletRequestParameterException e){
        HttpHeaders httpHeaders = new HttpHeaders();
        HttpStatus code = HttpStatus.BAD_REQUEST;

        Map<String,String> map = new HashMap<>();
        map.put("error type", code.getReasonPhrase());
        map.put("code", String.valueOf(code.value()));
        map.put("message","페이지 크기와 페이지 번호를 모두 입력해주세요!");

        return new ResponseEntity<>(map,httpHeaders, code);
    }
}
