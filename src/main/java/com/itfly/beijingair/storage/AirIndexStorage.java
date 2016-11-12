package com.itfly.beijingair.storage;

import com.itfly.beijingair.entity.AirIndex;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Component;

import javax.sql.DataSource;

/**
 * Created by zhoufeiyu on 12/11/2016.
 */
@Component
public class AirIndexStorage {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public void add(AirIndex airIndex) {
        String sql = "insert into airindex values (?,?,?,?)";
        jdbcTemplate.update(sql, airIndex.getTimestamp(), airIndex.getConcentration(), airIndex.getAqi(), airIndex.getDefinition());
    }

    public AirIndex getLatest() {
        String sql = "select * from airindex order by timestamp desc limit 1";
        return jdbcTemplate.queryForObject(sql, ROW_MAPPER);
    }

    private static final RowMapper<AirIndex> ROW_MAPPER = (resultSet, i) -> {
        AirIndex airIndex = new AirIndex();
        airIndex.setTimestamp(resultSet.getInt("timestamp"));
        airIndex.setConcentration(resultSet.getDouble("concentration"));
        airIndex.setAqi(resultSet.getInt("aqi"));
        airIndex.setDefinition(resultSet.getString("definition"));
        return airIndex;
    };
}
