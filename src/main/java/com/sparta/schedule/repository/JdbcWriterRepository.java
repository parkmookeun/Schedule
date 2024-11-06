package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.domain.Writer;
import com.sparta.schedule.dto.*;
import lombok.extern.slf4j.Slf4j;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.SQLTransactionRollbackException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
@Slf4j
public class JdbcWriterRepository implements WriterRepository{

    private final JdbcTemplate jdbcTemplate;

    public JdbcWriterRepository(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    @Override
    public Optional<Writer> save(Writer writer) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("writer").usingGeneratedKeyColumns("writer_id");
        Map<String, Object> parameters = new HashMap<>();

        //생성 시간 넣기
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        parameters.put("email", writer.getEmail());
        parameters.put("name",writer.getName());
        parameters.put("created_date", nowString);
        parameters.put("edit_date", nowString);

        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));

        // 생성 시간과 수정 시간 String 변환 후 리턴
        return Optional.of(new Writer(key.longValue(), writer.getName(), writer.getEmail(),
                nowString, nowString));
    }

    @Override
    public Optional<Writer> read(Long writerId) {
        List<Writer> result = jdbcTemplate.query("select * from writer where writer_id = ?", writerRowMapper(), writerId);
        return result.stream().findAny();
    }

    @Override
    public Optional<Writer> update(Long writerId, Writer writer) throws SQLTransactionRollbackException {
        int updated = jdbcTemplate.update("update writer set email = ?, name= ?, edit_date = CURRENT_TIME where writer_id = ?", writer.getEmail(), writer.getName(), writerId);
        //업데이트에 실패 하면
        if(updated == 0){
            throw new SQLTransactionRollbackException("수정되지않았습니다!");
        }
        return Optional.of(new Writer(writerId,writer.getName(),writer.getEmail(),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"))));
    }

    @Override
    public void delete(Long writerId) {
        jdbcTemplate.update("delete from writer where writer_id = ?", writerId);
    }

    private RowMapper<Writer> writerRowMapper() {
        return new RowMapper<Writer>() {
            @Override
            public Writer mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Writer(
                        rs.getLong("writer_id"),
                        rs.getString("name"),
                        rs.getString("email"),
                        rs.getString("created_date"),
                        rs.getString("edit_date")
                );
            }
        };
    }
}
