package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.domain.Writer;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class UpdateRequestDto {
    private String writer;
    private String password;
    private String todo;
}
