package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.CreateResponseDto;
import com.sparta.schedule.dto.ReadResponseDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;

import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

@Repository
public class JdbcScheduleRepository implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;

    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //DB에 저장
    @Override
    public CreateResponseDto save(Schedule schedule) {
        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id");
        Map<String, Object> parameters = new HashMap<>();
        //생성 시간 넣기
        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));
        parameters.put("writer_id",schedule.getWriterId());
        parameters.put("todo", schedule.getTodo());
        parameters.put("password",schedule.getPassword());
        parameters.put("created_date", nowString);
        parameters.put("edit_date", nowString);
        // 저장 후 생성된 key값을 Number 타입으로 반환하는 메서드
        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));
        // 생성 시간과 수정 시간 String 변환 후 리턴
        return new CreateResponseDto(key.longValue(), schedule.getWriterId(),
                schedule.getTodo(),nowString,nowString);
    }

    @Override
    public Optional<Schedule> findById(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where schedule_id = ?", scheduleRowMapper(), scheduleId);
        return result.stream().findAny();
    }
    //모든 목록 조회
    @Override
    public List<ReadResponseDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule order by edit_date desc", scheduleRowsMapper());
    }

    // 수정 날짜로 목록 조회
    @Override
    public List<ReadResponseDto> findAllSchedulesByEditDate(String date) {
        return jdbcTemplate.query("select * from schedule where DATE_FORMAT(edit_date,'%Y-%m-%d') = ? order by edit_date desc", scheduleRowsMapper(),date);
    }
    // 작성자명으로 목록 조회
    @Override
    public List<ReadResponseDto> findAllSchedulesByName(String writer) {
        return jdbcTemplate.query("select s.schedule_id, s.writer_id, s.todo, s.edit_date from schedule s inner join writer w on s.writer_id = w.writer_id where w.name = ? order by s.edit_date desc", scheduleRowsMapper(),writer);
    }
    // 수정 날짜 & 작성자명으로 목록 조회
    @Override
    public List<ReadResponseDto> findAllSchedulesByEditDateAndName(String writer, String date) {
        return jdbcTemplate.query("select s.schedule_id, s.writer_id, s.todo, s.edit_date\n" +
                "from schedule s inner join writer w on s.writer_id = w.writer_id\n" +
                "where w.name = ? and DATE_FORMAT(s.edit_date,'%Y-%m-%d') = ?\n" +
                "order by s.edit_date desc;", scheduleRowsJoinMapper(),writer,date);
    }

    private RowMapper<ReadResponseDto> scheduleRowsJoinMapper() {
        return new RowMapper<ReadResponseDto>() {
            @Override
            public ReadResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ReadResponseDto(
                        rs.getLong("s.schedule_id"),
                        rs.getLong("s.writer_id"),
                        rs.getString("s.todo"),
                        rs.getString("s.edit_date")
                );
            }

        };
    }

    private RowMapper<ReadResponseDto> scheduleRowsMapper() {
        return new RowMapper<ReadResponseDto>() {
            @Override
            public ReadResponseDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ReadResponseDto(
                        rs.getLong("schedule_id"),
                        rs.getLong("writer_id"),
                        rs.getString("todo"),
                        rs.getString("edit_date")
                );
            }

        };
    }

    private RowMapper<Schedule> scheduleRowMapper() {
        return new RowMapper<Schedule>() {
            @Override
            public Schedule mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new Schedule(
                        rs.getLong("schedule_id"),
                        rs.getLong("writer_id"),
                        rs.getString("todo"),
                        rs.getString("password"),
                        rs.getString("created_date"),
                        rs.getString("edit_date")
                );
            }

        };
    }

}

