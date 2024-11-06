package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Writer;
import com.sparta.schedule.dto.WriterCreateResDto;

import java.sql.SQLTransactionRollbackException;
import java.util.Optional;

public interface WriterRepository {
    //저장
    Optional<Writer> save(Writer writer);
    //조회(단건만 구현)
    Optional<Writer> read(Long writerId);
    //수정
    Optional<Writer> update(Long writerId, Writer writer) throws SQLTransactionRollbackException;
    //삭제
    void delete(Long writerId);
}
