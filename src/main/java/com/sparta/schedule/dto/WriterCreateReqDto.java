package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WriterCreateReqDto {
    private String email;
    private String name;
}
