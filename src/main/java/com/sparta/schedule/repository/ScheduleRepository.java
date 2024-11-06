package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.ScheduleCreateResDto;
import com.sparta.schedule.dto.ScheduleReadResDto;
import com.sparta.schedule.dto.ScheduleUpdateReqDto;
import com.sparta.schedule.dto.ScheduleUpdateResDto;

import java.sql.SQLTransactionRollbackException;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleCreateResDto save(Schedule schedule);

    Optional<Schedule> findById(Long scheduleId);
    List<ScheduleReadResDto> findAllSchedules();
    List<ScheduleReadResDto> findAllSchedulesByEditDate(String date);
    List<ScheduleReadResDto> findAllSchedulesByName(String writer);
    List<ScheduleReadResDto> findAllSchedulesByEditDateAndName(String writer, String date);

    ScheduleUpdateResDto update(ScheduleUpdateReqDto dto, Long scheduleId) throws SQLTransactionRollbackException;

    int delete(Long scheduleId, String password);
}
