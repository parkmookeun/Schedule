package com.sparta.schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Writer {
    private Long writer_id;
    private String name;
    private String email;
    private LocalDateTime created_date;
    private LocalDateTime edit_date;
}
