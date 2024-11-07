package com.sparta.schedule.controller;

import com.sparta.schedule.dto.*;
import com.sparta.schedule.service.WriterService;
import jakarta.validation.Valid;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * 작성자 컨트롤러
 */
@RestController
@RequestMapping("/api/writers")
@RequiredArgsConstructor
public class WriterController {

    private final WriterService service;

    /**
     * 유저생성요청을 받는 함수 {@code HTTP_METHOD:POST}
     * @param dto - 유저생성요청 DTO
     * @return 성공시 {@code CREATED(201}과 유저생성응답 DTO 반환
     */
    @PostMapping
    public ResponseEntity<WriterCreateResDto> createWriter(@Valid @RequestBody  WriterCreateReqDto dto) {

        WriterCreateResDto writerCreateResDto = service.saveWriter(dto);

        return new ResponseEntity<>(writerCreateResDto, HttpStatus.CREATED);
    }

    /**
     * 유저조회요청을 받는 함수 {@code HTTP_METHOD:GET}
     * @param writerId - 유저 아이디
     * @return 성공시 {@code OK(200)}과 유저조회응답 DTO 반환
     */
    @GetMapping("/{writerId}")
    public ResponseEntity<WriterReadResDto> readWriter(@PathVariable Long writerId) {

        WriterReadResDto writerReadResDto = service.readWriter(writerId);

        return new ResponseEntity<>(writerReadResDto,HttpStatus.OK);
    }

    /**
     * 유저수정요청을 받는 함수 {@code HTTP_METHOD:PUT}
     * @param writerId - 유저 아이디
     * @param dto - 유저수정요청 DTO
     * @return 성공시 {@code OK(200)}과 유저수정응답 DTO 반환
     */
    @PutMapping("/{writerId}")
    public ResponseEntity<WriterUpdateResDto> updateWriter(@PathVariable Long writerId,
                                                           @Valid @RequestBody  WriterUpdateReqDto dto) {

            return new ResponseEntity<>(service.updateWriter(writerId, dto), HttpStatus.OK);

    }

    /**
     * 유저삭제요청을 받는 함수 {@code HTTP_METHOD:DELETE}
     * @param writerId - 유저아이디
     * @return 성공시 {@code OK(200)} 반환
     */
    @DeleteMapping("/{writerId}")
    public ResponseEntity<String> deleteWriter(@PathVariable Long writerId) {

        service.deleteWriter(writerId);

        return new  ResponseEntity<>(HttpStatus.OK);
    }
}
