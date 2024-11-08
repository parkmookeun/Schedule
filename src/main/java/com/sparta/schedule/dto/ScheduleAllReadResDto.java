package com.sparta.schedule.dto;

import com.sparta.schedule.domain.Schedule;
import lombok.AllArgsConstructor;
import lombok.Getter;

@Getter
@AllArgsConstructor
public class ScheduleAllReadResDto {

    private final Long scheduleId;
    private final Long writerId;
    private final String writer;
    private final String todo;
    private final String editDate;

}
