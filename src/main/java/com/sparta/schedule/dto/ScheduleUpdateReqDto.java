package com.sparta.schedule.dto;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateReqDto {
    private String writer;
    @NotNull
    private String password;
    @NotNull
    @Size(max=200)
    private String todo;
}
