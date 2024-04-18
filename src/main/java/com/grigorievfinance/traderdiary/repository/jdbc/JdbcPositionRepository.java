package com.grigorievfinance.traderdiary.repository.jdbc;

import com.grigorievfinance.traderdiary.model.Position;
import com.grigorievfinance.traderdiary.repository.PositionRepository;
import com.grigorievfinance.traderdiary.util.ValidationUtil;
import org.springframework.dao.support.DataAccessUtils;
import org.springframework.jdbc.core.BeanPropertyRowMapper;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.namedparam.MapSqlParameterSource;
import org.springframework.jdbc.core.namedparam.NamedParameterJdbcTemplate;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import org.springframework.stereotype.Repository;
import org.springframework.transaction.annotation.Transactional;

import java.time.LocalDateTime;
import java.util.List;

@Repository
@Transactional(readOnly = true)
public class JdbcPositionRepository implements PositionRepository {

    private static final RowMapper<Position> ROW_MAPPER = BeanPropertyRowMapper.newInstance(Position.class);
    private final JdbcTemplate jdbcTemplate;
    private final NamedParameterJdbcTemplate namedParameterJdbcTemplate;
    private final SimpleJdbcInsert insertPosition;

    public JdbcPositionRepository(JdbcTemplate jdbcTemplate, NamedParameterJdbcTemplate namedParameterJdbcTemplate) {
        this.insertPosition = new SimpleJdbcInsert(jdbcTemplate).withTableName("position").usingGeneratedKeyColumns("id");
        this.jdbcTemplate = jdbcTemplate;
        this.namedParameterJdbcTemplate = namedParameterJdbcTemplate;
    }

    @Override
    @Transactional
    public Position save(Position position, int userId) {
        ValidationUtil.validate(position);
        MapSqlParameterSource map = new MapSqlParameterSource()
                .addValue("id", position.getId())
                .addValue("symbol", position.getSymbol())
                .addValue("profit", position.getProfitLoss())
                .addValue("date_time", position.getDateTime())
                .addValue("user_id", userId);
        if (position.isNew()) {
            Number newId = insertPosition.executeAndReturnKey(map);
            position.setId(newId.intValue());
        } else {
            if (namedParameterJdbcTemplate.update("UPDATE position SET symbol=:symbol, profit=:profit, date_time=:date_time WHERE id=:id AND user_id=:user_id",
                    map) == 0) {
                return null;
            }
        }
        return position;
    }

    @Override
    @Transactional
    public boolean delete(int id, int userId) {
        return jdbcTemplate.update("DELETE FROM position WHERE id=? AND user_id=?", id, userId) != 0;
    }

    @Override
    public Position get(int id, int userId) {
        List<Position> positions = jdbcTemplate.query("SELECT * FROM position WHERE id=? AND user_id=?", ROW_MAPPER, id, userId);
        return DataAccessUtils.singleResult(positions);
    }

    @Override
    public List<Position> getAll(int userId) {
        return jdbcTemplate.query("SELECT * FROM position WHERE user_id=? ORDER BY date_time DESC", ROW_MAPPER, userId);
    }

    @Override
    public List<Position> getBetweenHalfOpen(LocalDateTime startDateTime, LocalDateTime endDateTime, int userId) {
        return jdbcTemplate.query("SELECT * FROM position WHERE user_id=? AND date_time >= ? AND date_time < ? ORDER BY date_time DESC",
                ROW_MAPPER, userId, startDateTime, endDateTime);
    }
}
