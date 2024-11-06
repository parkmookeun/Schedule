package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleReadResDto {

    private final Long scheduleId;
    private final Long writerId;
    private final String todo;
    private final String createdDate;

    public ScheduleReadResDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.writerId = schedule.getWriterId();
        this.todo = schedule.getTodo();
        this.createdDate = schedule.getCreatedDate();
    }
}
