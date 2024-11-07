package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Writer;

import java.util.Optional;

public interface WriterRepository {
    Optional<Writer> save(Writer writer);
    Optional<Writer> read(Long writerId);
    Optional<Writer> update(Long writerId, Writer writer);
    void delete(Long writerId);
}
