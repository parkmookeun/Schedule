package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriterUpdateReqDto {
    private String email;
    private String name;
}
