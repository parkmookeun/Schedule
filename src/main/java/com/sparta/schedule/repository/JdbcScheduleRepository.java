package com.sparta.schedule.repository;

import com.sparta.schedule.domain.Schedule;
import com.sparta.schedule.dto.*;
import com.sparta.schedule.exception.PasswordNotCorrectException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.util.StringUtils;
import javax.sql.DataSource;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

/**
 * 일정 리포지토리
 */
@Repository
public class JdbcScheduleRepository implements ScheduleRepository{

    private final JdbcTemplate jdbcTemplate;

    private final int All_UPDATED = 2;

    private final int TODO_UPDATED = 1;

    public JdbcScheduleRepository(DataSource dataSource) {

        this.jdbcTemplate = new JdbcTemplate(dataSource);

    }

    /**
     *
     * @param schedule 일정 객체
     * @return 일정생성응답 DTO
     */
    @Override
    public ScheduleCreateResDto save(Schedule schedule) {

        SimpleJdbcInsert jdbcInsert = new SimpleJdbcInsert(jdbcTemplate);
        jdbcInsert.withTableName("schedule").usingGeneratedKeyColumns("schedule_id");

        Map<String, Object> parameters = new HashMap<>();


        LocalDateTime now = LocalDateTime.now();
        String nowString = now.format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm"));

        parameters.put("writer_id",schedule.getWriterId());
        parameters.put("todo", schedule.getTodo());
        parameters.put("password",schedule.getPassword());
        parameters.put("created_date", nowString);
        parameters.put("edit_date", nowString);


        Number key = jdbcInsert.executeAndReturnKey(new MapSqlParameterSource(parameters));


        return new ScheduleCreateResDto(key.longValue(), schedule.getWriterId(),
                schedule.getTodo(),nowString,nowString);
    }

    /**
     *
     * @param scheduleId - 일정 아이디
     * @return Optional 일정 객체
     */
    @Override
    public Optional<Schedule> findById(Long scheduleId) {

        List<Schedule> result = jdbcTemplate.query("select * from schedule where schedule_id = ?", scheduleRowMapper(), scheduleId);

        return result.stream().findAny();
    }

    /**
     *
     * @param writer 작성자명 옵션
     * @param date 날짜 옵션
     * @param pageNumber 페이지 번호 필수
     * @param pageSize 페이지 크기 필수
     * @return 일정목록응답 리스트 반환
     */
    @Override
    public List<ScheduleAllReadResDto> findAllSchedulesByEditDateOrName(String writer, String date, Integer pageNumber, Integer pageSize) {

        StringBuilder sb =
                new StringBuilder("select s.schedule_id, s.writer_id, w.name, s.todo, s.edit_date from schedule s inner join writer w on s.writer_id = w.writer_id where 1=1");

        if (StringUtils.hasText(writer)) {
            sb.append(" and w.name = '")
                    .append(writer)
                    .append("'");

        }
        if (StringUtils.hasText(date)) {
            sb.append(" and DATE_FORMAT(s.edit_date,'%Y-%m-%d') = '")
                    .append(date)
                    .append("'");

        }
        sb.append(" order by s.edit_date desc limit ")
                .append((pageNumber-1)*pageSize)
                .append(", ")
                .append(pageSize);

        return jdbcTemplate.query(new String(sb),scheduleRowsJoinMapper());
    }

    /**
     *
     * @param dto - 일정수정요청 DTO
     * @param scheduleId - 일정아이디
     * @return 일정수정응답 DTO
     */
    @Override
    @Transactional
    public ScheduleUpdateResDto update(ScheduleUpdateReqDto dto, Long scheduleId){
        //스케줄 todo 수정
        int todoUpdated = jdbcTemplate.update("update schedule set todo = ?, edit_date = CURRENT_TIME where schedule_id = ? and password = ?",
                dto.getTodo(), scheduleId, dto.getPassword());

        //스케줄 todo가 수정되면
        int writerUpdated = 0;
        if (todoUpdated == TODO_UPDATED) {

            writerUpdated = jdbcTemplate.update("update writer set name = ?, edit_date = CURRENT_TIME where writer.writer_id = (select schedule.writer_id from schedule where schedule.schedule_id = ?)",
                    dto.getWriter(), scheduleId);
        }


        //수정이 모두 이루어지지 않았다면
        if (todoUpdated + writerUpdated != All_UPDATED) {

            throw new PasswordNotCorrectException("패스워드가 일치하지 않거나 없는 아이디 입니다.");

        }
        //성공했다면
        return new ScheduleUpdateResDto(scheduleId,dto.getWriter(),dto.getTodo(),
                LocalDateTime.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm")));
    }

    /**
     *
     * @param scheduleId - 일정아이디
     * @param password - 일정비밀번호
     * @return 삭제 됐으면 1, 안됐으면 0 반환
     */
    @Override
    public int delete(Long scheduleId, String password) {

        return jdbcTemplate.update("delete from schedule where schedule_id = ? and password = ?", scheduleId, password);

    }

    /**
     *
     * @return 일정목록조회DTO 매퍼
     */
    private RowMapper<ScheduleAllReadResDto> scheduleRowsJoinMapper() {

        return new RowMapper<ScheduleAllReadResDto>() {
            @Override
            public ScheduleAllReadResDto mapRow(ResultSet rs, int rowNum) throws SQLException {

                return new ScheduleAllReadResDto(
                        rs.getLong("s.schedule_id"),
                        rs.getLong("s.writer_id"),
                        rs.getString("w.name"),
                        rs.getString("s.todo"),
                        rs.getString("s.edit_date")
                );
            }
        };
    }

    /**
     *
     * @return 일정 매퍼
     */
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

