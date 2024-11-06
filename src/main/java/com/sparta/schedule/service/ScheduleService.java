package com.sparta.schedule.service;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.*;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLTransactionRollbackException;
import java.util.List;
import java.util.Optional;

@Service
public class ScheduleService {
    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {
        this.repository = repository;
    }
    //생성 서비스 함수
    public CreateResponseDto saveSchedule(CreateRequestDto requestDto) {

        // 요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(requestDto.getWriterId(), requestDto.getPassword(),requestDto.getTodo());

        // 저장
        return repository.save(schedule);
    }
    //조회 서비스 함수
    public ReadResponseDto readSchedule(Long scheduleId){
        Optional<Schedule> optionalSchedule = repository.findById(scheduleId);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ReadResponseDto(optionalSchedule.get());

        //리턴
    }
    public List<ReadResponseDto> readAllSchedule(String writer, String date){
        if(writer == null && date == null){
            return repository.findAllSchedules();
        }else if(writer == null){
            return repository.findAllSchedulesByEditDate(date);
        }else if(date == null){
            return repository.findAllSchedulesByName(writer);
        }else{
            return repository.findAllSchedulesByEditDateAndName(writer,date);
        }
    }
    //수정 서비스 로직
    public UpdateResponseDto updateSchedule(UpdateRequestDto dto, Long scheduleId) throws SQLTransactionRollbackException {
        if (dto.getTodo() == null || dto.getPassword() == null || dto.getWriter() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            return repository.update(dto, scheduleId);
    }

    public void deleteSchedule(Long scheduleId, DeleteRequestDto dto) {
        int delete = repository.delete(scheduleId,dto.getPassword());
        if(delete == 0){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
    }
}
