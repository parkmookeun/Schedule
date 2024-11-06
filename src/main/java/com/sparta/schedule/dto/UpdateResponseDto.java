package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateResponseDto {
    private Long scheduleId;
    private String writer;
    private String todo;
    private String editDate;
}
