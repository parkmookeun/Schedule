package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.*;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleCreateResDto save(Schedule schedule);

    Optional<Schedule> findById(Long scheduleId);
    List<ScheduleAllReadResDto> findAllSchedulesByEditDateOrName(String writer, String date, Integer pageNumber, Integer pageSize);

    ScheduleUpdateResDto update(ScheduleUpdateReqDto dto, Long scheduleId);

    int delete(Long scheduleId, String password);
}
