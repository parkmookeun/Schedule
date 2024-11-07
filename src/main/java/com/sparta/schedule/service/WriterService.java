package com.sparta.schedule.service;

import com.sparta.schedule.domain.Writer;
import com.sparta.schedule.dto.*;
import com.sparta.schedule.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;
import java.util.Optional;

/**
 * 작성자 서비스(유저 서비스)
 */
@Service
@RequiredArgsConstructor
public class WriterService {

    private final WriterRepository repository;

    /**
     *
     * @param dto - 작성자생성요청 DTO
     * @return 작성자생성응답 DTO
     */
    public WriterCreateResDto saveWriter(WriterCreateReqDto dto) {

        Writer writer = new Writer(dto.getName(),dto.getEmail());

        Writer savedWriter = repository.save(writer).orElseThrow();

        return new WriterCreateResDto(savedWriter.getWriterId(),savedWriter.getEmail(),
                savedWriter.getName(),savedWriter.getCreatedDate(),savedWriter.getEditDate());
    }

    /**
     *
     * @param writerId - 작성자아이디
     * @return 작성자조회응답 DTO
     */
    public WriterReadResDto readWriter(Long writerId) {
        Optional<Writer> optionalWriter = repository.read(writerId);

        if(optionalWriter.isEmpty()){

            throw new ResponseStatusException(HttpStatus.NOT_FOUND);

        }
        Writer findWriter = optionalWriter.get();

        return new WriterReadResDto(findWriter.getWriterId(),findWriter.getEmail(),findWriter.getName());
    }

    /**
     *
     * @param writerId - 작성자아이디
     * @param dto - 작성자수정요청 DTO
     * @return 작성자수정응답 DTO
     */
    public WriterUpdateResDto updateWriter(Long writerId, WriterUpdateReqDto dto){

        Writer writer = new Writer(dto.getName(),dto.getEmail());

        Optional<Writer> optionalWriter = repository.update(writerId, writer);

        Writer updatedWriter = optionalWriter.get();

        return new WriterUpdateResDto(updatedWriter.getWriterId(), updatedWriter.getEmail(),
                updatedWriter.getName(), updatedWriter.getEditDate());
    }

    /**
     *
     * @param writerId - 작성자 아이디
     */
    public void deleteWriter(Long writerId) {

        repository.delete(writerId);

    }
}
