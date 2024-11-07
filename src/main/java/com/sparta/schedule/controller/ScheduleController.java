package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.exception.PasswordNotCorrectException;
import com.sparta.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLTransactionRollbackException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;
    //Create - 생성
    @PostMapping
    public ResponseEntity<ScheduleCreateResDto> createSchedule(@RequestBody @Valid ScheduleCreateReqDto dto){
        return new ResponseEntity<>(service.saveSchedule(dto), HttpStatus.CREATED);
    }
    //Read - 단건 조회
    @GetMapping("/{scheduleId}")
    public ScheduleReadResDto readSchedule(@PathVariable Long scheduleId){
        //id에 맞는 스케줄 리턴
        return service.readSchedule(scheduleId);
    }

    //Read - 다건 조회
    @GetMapping
    public List<ScheduleAllReadResDto> readAllSchedule(@RequestParam(required = false) String  writer,
                                                    @RequestParam(required = false) String date,
                                                    @RequestParam int pageNumber,
                                                    @RequestParam int pageSize){

        return service.readAllSchedule(writer, date, pageNumber, pageSize);
    }
    //Update - 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleUpdateReqDto dto){
        try {
            return new ResponseEntity<>(service.updateSchedule(dto, scheduleId), HttpStatus.OK);
        } catch (SQLTransactionRollbackException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    //Delete - 삭제
    @DeleteMapping("{scheduleId}")
    public ResponseEntity<Long> deleteSchedule(@PathVariable Long scheduleId, @RequestBody @Valid ScheduleDeleteReqDto dto){
        service.deleteSchedule(scheduleId, dto);
        return new ResponseEntity<>(scheduleId,HttpStatus.OK);
    }

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
}
