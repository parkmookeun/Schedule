package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.dto.ReadResponseDto;

import java.util.List;
import java.util.Optional;

public interface ScheduleRepository {
    CreateResponseDto save(Schedule schedule);

    Optional<Schedule> findById(Long scheduleId);
    public List<ReadResponseDto> findAllSchedules();
    public List<ReadResponseDto> findAllSchedulesByEditDate(String date);
    public List<ReadResponseDto> findAllSchedulesByName(String writer);
    public List<ReadResponseDto> findAllSchedulesByEditDateAndName(String writer, String date);
}
