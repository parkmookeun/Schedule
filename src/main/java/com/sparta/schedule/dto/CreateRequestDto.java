package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class CreateRequestDto {
    private final Long writerId;
    private final String password;
    private final String todo;


    public CreateRequestDto(Schedule schedule){
        this.writerId = schedule.getScheduleId();
        this.password = schedule.getPassword();
        this.todo = schedule.getTodo();
    }
}
