package com.sparta.schedule.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@AllArgsConstructor
@Getter
public class WriterCreateReqDto {
    @Email
    private String email;
    private String name;
}
