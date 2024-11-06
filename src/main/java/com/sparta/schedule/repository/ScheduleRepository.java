package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.*;

import java.sql.SQLTransactionRollbackException;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    ScheduleCreateResDto save(Schedule schedule);

    Optional<Schedule> findById(Long scheduleId);
    List<ScheduleAllReadResDto> findAllSchedules(Integer pageNumber, Integer pageSize);
    List<ScheduleAllReadResDto> findAllSchedulesByEditDate(String date, Integer pageNumber, Integer pageSize);
    List<ScheduleAllReadResDto> findAllSchedulesByName(String writer, Integer pageNumber, Integer pageSize);
    List<ScheduleAllReadResDto> findAllSchedulesByEditDateAndName(String writer, String date, Integer pageNumber, Integer pageSize);

    ScheduleUpdateResDto update(ScheduleUpdateReqDto dto, Long scheduleId) throws SQLTransactionRollbackException;

    int delete(Long scheduleId, String password);
}
