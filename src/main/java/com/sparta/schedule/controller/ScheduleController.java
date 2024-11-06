package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLTransactionRollbackException;
import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;
    //Create - 생성
    @PostMapping
    public ResponseEntity<ScheduleCreateResDto> createSchedule(@RequestBody ScheduleCreateReqDto dto){
        return new ResponseEntity<>(service.saveSchedule(dto), HttpStatus.CREATED) ;
    }
    //Read - 단건 조회
    @GetMapping("/{scheduleId}")
    public ScheduleReadResDto readSchedule(@PathVariable Long scheduleId){
        //id에 맞는 스케줄 리턴
        return service.readSchedule(scheduleId);
    }
    //Read - 다건 조회
    @GetMapping
    public List<ScheduleReadResDto> readAllSchedule(@RequestParam(required = false) String  writer, @RequestParam(required = false) String date){
        return service.readAllSchedule(writer, date);
    }
    //Update - 수정
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResDto> updateSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleUpdateReqDto dto){
        try {
            return new ResponseEntity<>(service.updateSchedule(dto, scheduleId), HttpStatus.OK);
        } catch (SQLTransactionRollbackException e) {
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    //Delete - 삭제
    @DeleteMapping("{scheduleId}")
    public ResponseEntity<Long> deleteSchedule(@PathVariable Long scheduleId, @RequestBody ScheduleDeleteReqDto dto){
        service.deleteSchedule(scheduleId, dto);
        return new ResponseEntity<>(scheduleId,HttpStatus.OK);
    }
}
