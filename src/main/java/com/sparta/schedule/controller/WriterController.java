package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.service.WriterService;
import lombok.RequiredArgsConstructor;
import org.apache.coyote.Response;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.sql.SQLTransactionRollbackException;

@RestController
@RequestMapping("/api/writers")
@RequiredArgsConstructor
public class WriterController {
    private final WriterService service;

    @PostMapping
    public ResponseEntity<WriterCreateResDto> createWriter(@RequestBody WriterCreateReqDto dto){
        WriterCreateResDto writerCreateResDto = service.saveWriter(dto);
        return new ResponseEntity<>(writerCreateResDto, HttpStatus.CREATED);
    }

    @GetMapping("/{writerId}")
    public ResponseEntity<WriterReadResDto> readWriter(@PathVariable Long writerId){
        WriterReadResDto writerReadResDto = service.readWriter(writerId);
        return new ResponseEntity<>(writerReadResDto,HttpStatus.OK);
    }

    @PutMapping("/{writerId}")
    public ResponseEntity<WriterUpdateResDto> updateWriter(@PathVariable Long writerId,
                                                           @RequestBody WriterUpdateReqDto dto){
        try {
            return new ResponseEntity<>(service.updateWriter(writerId, dto), HttpStatus.OK);
        }catch (SQLTransactionRollbackException e){
            return new ResponseEntity<>(HttpStatus.NOT_MODIFIED);
        }
    }

    @DeleteMapping("/{writerId}")
    public ResponseEntity<String> deleteWriter(@PathVariable Long writerId){
        service.deleteWriter(writerId);
        return new  ResponseEntity<>(HttpStatus.OK);
    }
}
