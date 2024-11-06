package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateReqDto {
    private String writer;
    private String password;
    private String todo;
}
