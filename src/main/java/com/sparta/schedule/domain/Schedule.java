package com.sparta.schedule.domain;


import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.NotNull;
import lombok.AllArgsConstructor;
import lombok.Getter;

import java.time.LocalDateTime;

@Getter
@AllArgsConstructor
public class Schedule {

    private Long scheduleId;

    private Long writerId;

    private String todo;

    private String password;

    private String createdDate;

    private String editDate;

    public Schedule(Long writerId, String password, String todo){
        this.writerId = writerId;
        this.password = password;
        this.todo = todo;
    }

    public void update(String todo){
        this.todo = todo;
    }
}
