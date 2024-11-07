package com.sparta.schedule.domain;

import lombok.AllArgsConstructor;
import lombok.Getter;

/**
 * 유저 Entity
 */
@Getter
@AllArgsConstructor
public class Writer {
    private Long writerId;
    private String name;
    private String email;
    private String createdDate;
    private String editDate;

    public Writer(String name, String email){
        this.name = name;
        this.email = email;
    }
}
