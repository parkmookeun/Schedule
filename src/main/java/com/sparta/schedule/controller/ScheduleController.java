package com.sparta.schedule.controller;

import com.sparta.schedule.dto.CreateRequestDto;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.dto.ReadResponseDto;
import com.sparta.schedule.service.ScheduleService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;
    //Create - 생성
    @PostMapping
    public CreateResponseDto createSchedule(@RequestBody CreateRequestDto dto){
        return service.saveSchedule(dto);
    }
    //Read - 단건 조회
    @GetMapping("/{scheduleId}")
    public ReadResponseDto readSchedule(@PathVariable Long scheduleId){
        //id에 맞는 스케줄 리턴
        return service.readSchedule(scheduleId);
    }
    //Read - 다건 조회
    @GetMapping
    public List<ReadResponseDto> readAllSchedule(@RequestParam(required = false) String  writer, @RequestParam(required = false) String date){
        return service.readAllSchedule(writer, date);
    }
}
