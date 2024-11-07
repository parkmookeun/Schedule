package com.sparta.schedule.service;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.*;
import com.sparta.schedule.exception.PasswordNotCorrectException;
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
    public ScheduleCreateResDto saveSchedule(ScheduleCreateReqDto requestDto) {

        // 요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(requestDto.getWriterId(), requestDto.getPassword(),requestDto.getTodo());

        // 저장
        return repository.save(schedule);
    }
    //조회 서비스 함수
    public ScheduleReadResDto readSchedule(Long scheduleId){
        Optional<Schedule> optionalSchedule = repository.findById(scheduleId);

        if(optionalSchedule.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        return new ScheduleReadResDto(optionalSchedule.get());
        //리턴
    }

    public List<ScheduleAllReadResDto> readAllSchedule(String writer, String date, Integer pageNumber, Integer pageSize){
        if(writer == null && date == null){
            return repository.findAllSchedules(pageNumber,pageSize);
        }else if(writer == null){
            return repository.findAllSchedulesByEditDate(date,pageNumber,pageSize);
        }else if(date == null){
            return repository.findAllSchedulesByName(writer,pageNumber,pageSize);
        }else{
            return repository.findAllSchedulesByEditDateAndName(writer,date,pageNumber,pageSize);
        }
    }

    //수정 서비스 로직
    public ScheduleUpdateResDto updateSchedule(ScheduleUpdateReqDto dto, Long scheduleId) throws SQLTransactionRollbackException {
        if (dto.getTodo() == null || dto.getPassword() == null || dto.getWriter() == null){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
            return repository.update(dto, scheduleId);
    }

    public void deleteSchedule(Long scheduleId, ScheduleDeleteReqDto dto) {
        int delete = repository.delete(scheduleId,dto.getPassword());
        if(delete == 0){
            throw new PasswordNotCorrectException("패스워드가 일치하지 않습니다!");
        }
    }
}
