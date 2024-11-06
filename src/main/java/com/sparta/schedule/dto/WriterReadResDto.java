package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriterReadResDto {
    private Long writerId;
    private String email;
    private String name;
}
