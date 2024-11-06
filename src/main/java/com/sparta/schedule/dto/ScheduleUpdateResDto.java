package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleUpdateResDto {
    private Long scheduleId;
    private String writer;
    private String todo;
    private String editDate;
}
