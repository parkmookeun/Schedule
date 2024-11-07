package com.sparta.schedule.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateReqDto {
    @NotBlank
    private String writer;

    @NotBlank
    private String password;

    @NotBlank
    @Size(max=200)
    private String todo;
}
