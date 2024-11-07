package com.sparta.schedule.dto;

import jakarta.validation.constraints.Email;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class WriterUpdateReqDto {
    @Email
    private String email;
    private String name;
}
