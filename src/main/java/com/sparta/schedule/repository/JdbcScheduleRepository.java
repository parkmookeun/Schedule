package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.ScheduleCreateResDto;
import com.sparta.schedule.dto.ScheduleReadResDto;
import com.sparta.schedule.dto.ScheduleUpdateReqDto;
import com.sparta.schedule.dto.ScheduleUpdateResDto;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

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
public class JdbcScheduleRepository implements ScheduleRepository{
    private final JdbcTemplate jdbcTemplate;
    private final int All_UPDATED = 2;
    public JdbcScheduleRepository(DataSource dataSource) {
        this.jdbcTemplate = new JdbcTemplate(dataSource);
    }

    //DB에 저장
    @Override
    public ScheduleCreateResDto save(Schedule schedule) {
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
        return new ScheduleCreateResDto(key.longValue(), schedule.getWriterId(),
                schedule.getTodo(),nowString,nowString);
    }

    @Override
    public Optional<Schedule> findById(Long scheduleId) {
        List<Schedule> result = jdbcTemplate.query("select * from schedule where schedule_id = ?", scheduleRowMapper(), scheduleId);
        return result.stream().findAny();
    }
    //모든 목록 조회
    @Override
    public List<ScheduleReadResDto> findAllSchedules() {
        return jdbcTemplate.query("select * from schedule order by edit_date desc", scheduleRowsMapper());
    }

    // 수정 날짜로 목록 조회
    @Override
    public List<ScheduleReadResDto> findAllSchedulesByEditDate(String date) {
        return jdbcTemplate.query("select * from schedule where DATE_FORMAT(edit_date,'%Y-%m-%d') = ? order by edit_date desc", scheduleRowsMapper(),date);
    }
    // 작성자명으로 목록 조회
    @Override
    public List<ScheduleReadResDto> findAllSchedulesByName(String writer) {
        return jdbcTemplate.query("select s.schedule_id, s.writer_id, s.todo, s.edit_date from schedule s inner join writer w on s.writer_id = w.writer_id where w.name = ? order by s.edit_date desc", scheduleRowsMapper(),writer);
    }
    // 수정 날짜 & 작성자명으로 목록 조회
    @Override
    public List<ScheduleReadResDto> findAllSchedulesByEditDateAndName(String writer, String date) {
        return jdbcTemplate.query("select s.schedule_id, s.writer_id, s.todo, s.edit_date\n" +
                "from schedule s inner join writer w on s.writer_id = w.writer_id\n" +
                "where w.name = ? and DATE_FORMAT(s.edit_date,'%Y-%m-%d') = ?\n" +
                "order by s.edit_date desc;", scheduleRowsJoinMapper(),writer,date);
    }
    // 할일 및 작성자명 수정
    @Override
    @Transactional
    public ScheduleUpdateResDto update(ScheduleUpdateReqDto dto, Long scheduleId) throws SQLTransactionRollbackException {
        int todoUpdated = jdbcTemplate.update("update schedule set todo = ?, edit_date = CURRENT_TIME where schedule_id = ? and password = ?", dto.getTodo(), scheduleId, dto.getPassword());
        int writerUpdated = jdbcTemplate.update("update writer set name = ?, edit_date = CURRENT_TIME where writer.writer_id = (select schedule.writer_id from schedule where schedule.schedule_id = ?)", dto.getWriter(), scheduleId);

        //실패했다면
        if(todoUpdated + writerUpdated != All_UPDATED){
            throw new SQLTransactionRollbackException("할일 및 작성자명 둘다 수정되지않았습니다!");
        }
        //성공했다면
        return new ScheduleUpdateResDto(scheduleId,dto.getWriter(),dto.getTodo(),LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    @Override
    public int delete(Long scheduleId, String password) {
        return jdbcTemplate.update("delete from schedule where schedule_id = ? and password = ?", scheduleId, password);
    }

    private RowMapper<ScheduleReadResDto> scheduleRowsJoinMapper() {
        return new RowMapper<ScheduleReadResDto>() {
            @Override
            public ScheduleReadResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleReadResDto(
                        rs.getLong("s.schedule_id"),
                        rs.getLong("s.writer_id"),
                        rs.getString("s.todo"),
                        rs.getString("s.edit_date")
                );
            }
        };
    }

    private RowMapper<ScheduleReadResDto> scheduleRowsMapper() {
        return new RowMapper<ScheduleReadResDto>() {
            @Override
            public ScheduleReadResDto mapRow(ResultSet rs, int rowNum) throws SQLException {
                return new ScheduleReadResDto(
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

