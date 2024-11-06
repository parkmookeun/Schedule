package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.dto.ReadResponseDto;
import com.sparta.schedule.dto.UpdateRequestDto;
import com.sparta.schedule.dto.UpdateResponseDto;

import java.sql.SQLTransactionRollbackException;
import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    CreateResponseDto save(Schedule schedule);

    Optional<Schedule> findById(Long scheduleId);
    List<ReadResponseDto> findAllSchedules();
    List<ReadResponseDto> findAllSchedulesByEditDate(String date);
    List<ReadResponseDto> findAllSchedulesByName(String writer);
    List<ReadResponseDto> findAllSchedulesByEditDateAndName(String writer, String date);

    UpdateResponseDto update(UpdateRequestDto dto, Long scheduleId) throws SQLTransactionRollbackException;

    int delete(Long scheduleId, String password);
}
