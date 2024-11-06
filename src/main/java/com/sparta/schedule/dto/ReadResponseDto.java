package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ReadResponseDto {

    private final Long scheduleId;
    private final Long writerId;
    private final String todo;
    private final String createdDate;

    public ReadResponseDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.writerId = schedule.getWriterId();
        this.todo = schedule.getTodo();
        this.createdDate = schedule.getCreatedDate();
    }
}
