package com.sparta.schedule.service;

import com.sparta.schedule.domain.Writer;
import com.sparta.schedule.dto.*;
import com.sparta.schedule.repository.WriterRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import java.sql.SQLTransactionRollbackException;
import java.util.Optional;

@Service
@RequiredArgsConstructor
public class WriterService {
    private final WriterRepository repository;

    //Create
    public WriterCreateResDto saveWriter(WriterCreateReqDto dto){
        Writer writer = new Writer(dto.getName(),dto.getEmail());
        Writer savedWriter = repository.save(writer).orElseThrow();

        return new WriterCreateResDto(savedWriter.getWriterId(),savedWriter.getEmail(),
                savedWriter.getName(),savedWriter.getCreatedDate(),savedWriter.getEditDate());
    }

    //Read
    public WriterReadResDto readWriter(Long writerId){
        Optional<Writer> optionalWriter = repository.read(writerId);

        if(optionalWriter.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }
        Writer findWriter = optionalWriter.get();
        return new WriterReadResDto(findWriter.getWriterId(),findWriter.getEmail(),findWriter.getName());
    }

    //Update
    public WriterUpdateResDto updateWriter(Long writerId, WriterUpdateReqDto dto) throws SQLTransactionRollbackException {
        Writer writer = new Writer(dto.getName(),dto.getEmail());
        Optional<Writer> optionalWriter = repository.update(writerId, writer);
        if(optionalWriter.isEmpty()){
            throw new ResponseStatusException(HttpStatus.NOT_FOUND);
        }

        Writer updatedWriter = optionalWriter.get();

        return new WriterUpdateResDto(updatedWriter.getWriterId(), updatedWriter.getEmail(),
                updatedWriter.getName(), updatedWriter.getEditDate());
    }

    //Delete
    public void deleteWriter(Long writerId){
        repository.delete(writerId);
    }
}
