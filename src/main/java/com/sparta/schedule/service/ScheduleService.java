package com.sparta.schedule.service;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.*;
import com.sparta.schedule.exception.PasswordNotCorrectException;
import com.sparta.schedule.repository.ScheduleRepository;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.List;
import java.util.Optional;

/**
 * 일정 서비스
 */
@Service
public class ScheduleService {

    private final ScheduleRepository repository;

    public ScheduleService(ScheduleRepository repository) {

        this.repository = repository;

    }

    /**
     *
     * @param requestDto - 일정생성요청 DTO
     * @return 일정응답요청 DTO
     */
    public ScheduleCreateResDto saveSchedule(ScheduleCreateReqDto requestDto) {

        // 요청받은 데이터로 Schedule 객체 생성
        Schedule schedule = new Schedule(requestDto.getWriterId(), requestDto.getPassword(),requestDto.getTodo());

        // 저장
        return repository.save(schedule);
    }

    /**
     *
     * @param scheduleId - 일정 아이디
     * @return 일정조회응답 DTO
     */
    public ScheduleReadResDto readSchedule(Long scheduleId) {

        Optional<Schedule> optionalSchedule = repository.findById(scheduleId);

        if(optionalSchedule.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }

        return new ScheduleReadResDto(optionalSchedule.get());

    }

    /**
     *
     * @param writer - 작성자명
     * @param date - 수정날짜
     * @param pageNumber - 페이지 번호
     * @param pageSize - 페이지 크기
     * @return 일정목록응답 DTO
     */
    public List<ScheduleAllReadResDto> readAllSchedule(String writer, String date, Integer pageNumber, Integer pageSize) {
        return repository.findAllSchedulesByEditDateOrName(writer, date, pageNumber, pageSize);

    }

    /**
     *
     * @param dto - 일정수정요청 DTO
     * @param scheduleId - 일정아이디
     * @return 일정수정응답 DTO
     */
    public ScheduleUpdateResDto updateSchedule(ScheduleUpdateReqDto dto, Long scheduleId) {

            return repository.update(dto, scheduleId);

    }

    /**
     *
     * @param scheduleId - 일정아이디
     * @param dto - 일정삭제요청 DTO
     */
    public void deleteSchedule(Long scheduleId, ScheduleDeleteReqDto dto) {

        int delete = repository.delete(scheduleId,dto.getPassword());

        if(delete == 0){

            throw new PasswordNotCorrectException("패스워드가 일치하지 않거나 올바른 아이디가 아닙니다!");

        }
    }
}
