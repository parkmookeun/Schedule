package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleCreateReqDto {
    private final Long writerId;
    @NotNull
    private final String password;
    @NotNull
    @Size(max = 200)
    private final String todo;


    public ScheduleCreateReqDto(Schedule schedule){
        this.writerId = schedule.getScheduleId();
        this.password = schedule.getPassword();
        this.todo = schedule.getTodo();
    }
}
