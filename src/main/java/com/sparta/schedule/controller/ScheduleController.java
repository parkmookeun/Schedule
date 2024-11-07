package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.service.ScheduleService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;
import java.util.List;

/**
 * 일정 컨트롤러
 */
@RestController
@RequiredArgsConstructor
@RequestMapping("/api/schedules")
public class ScheduleController {

    private final ScheduleService service;


    /**
     * 일정 생성 요청을 받는 함수 {@code HTTP_METHOD:POST}
     * @param dto - 일정생성요청 DTO
     * @return 성공시, {@code CREATED(201)}과 일정생성응답 DTO반환
     */
    @PostMapping
    public ResponseEntity<ScheduleCreateResDto> createSchedule(@Valid @RequestBody  ScheduleCreateReqDto dto){
        return new ResponseEntity<>(service.saveSchedule(dto), HttpStatus.CREATED);
    }

    /**
     * 일정 조회 요청을 받는 함수 {@code HTTP_METHOD:GET}
     * @param scheduleId - 일정 아이디
     * @return 성공시, {@code OK(200)}과 일정조회응답 DTO반환
     */
    //Read - 단건 조회
    @GetMapping("/{scheduleId}")
    public ScheduleReadResDto readSchedule(@PathVariable Long scheduleId){
        //id에 맞는 스케줄 리턴
        return service.readSchedule(scheduleId);
    }

    /**
     * 일정 목록조회 요청을 받는 함수 {@code HTTP_METHOD:GET}
     * @param writer - 작성자명
     * @param date - 수정날짜
     * @param pageNumber - 페이지 번호
     * @param pageSize - 페이지 크기
     * @return 성공시, {@code OK(200)}과 일정목록응답 DTO반환
     */
    @GetMapping
    public List<ScheduleAllReadResDto> readAllSchedule(@RequestParam(required = false) String  writer,
                                                    @RequestParam(required = false) String date,
                                                    @RequestParam int pageNumber,
                                                    @RequestParam int pageSize){

        return service.readAllSchedule(writer, date, pageNumber, pageSize);
    }

    /**
     * 일정 수정 요청을 받는 함수 {@code HTTP_METHOD:PUT}
     * @param scheduleId - 일정 아이디
     * @param dto - 일정수정요청 DTO
     * @return 성공시 {@code OK(200)}과 일정수정응답 DTO반환
     */
    @PutMapping("/{scheduleId}")
    public ResponseEntity<ScheduleUpdateResDto> updateSchedule(@PathVariable Long scheduleId, @Valid @RequestBody  ScheduleUpdateReqDto dto){
            return new ResponseEntity<>(service.updateSchedule(dto, scheduleId), HttpStatus.OK);
    }

    /**
     * 일정 삭제 요청을 받는 함수 {@code HTTP_METHOD:DELETE}
     * @param scheduleId - 일정 아이디
     * @param dto - 일정삭제요청 DTO
     * @return 성공시 {@code OK(200)}과 삭제된 아이디반환
     */
    @DeleteMapping("{scheduleId}")
    public ResponseEntity<Long> deleteSchedule(@PathVariable Long scheduleId, @Valid @RequestBody  ScheduleDeleteReqDto dto){
        service.deleteSchedule(scheduleId, dto);
        return new ResponseEntity<>(scheduleId,HttpStatus.OK);
    }
}
