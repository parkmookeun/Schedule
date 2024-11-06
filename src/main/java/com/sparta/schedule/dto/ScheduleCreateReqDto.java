package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateReqDto {
    private final Long writerId;
    private final String password;
    private final String todo;


    public ScheduleCreateReqDto(Schedule schedule){
        this.writerId = schedule.getScheduleId();
        this.password = schedule.getPassword();
        this.todo = schedule.getTodo();
    }
}
