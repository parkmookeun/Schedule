package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateResDto {
    private final Long scheduleId;
    private final Long writerId;
    private final String todo;
    private final String createdDate;
    private final String editDate;


    public ScheduleCreateResDto(Schedule schedule){
        this.scheduleId = schedule.getScheduleId();
        this.writerId = schedule.getWriterId();
        this.todo = schedule.getTodo();
        this.createdDate = schedule.getCreatedDate();
        this.editDate = schedule.getEditDate();

    }
}
