package com.sparta.schedule.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriterCreateResDto {
    private Long writerId;
    private String email;
    private String name;
    private String createdDate;
    private String editDate;
}
