package com.saasestate.app.repository;

import com.saasestate.app.entity.EstateData;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;
import org.springframework.util.DigestUtils;
import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EstateDataRepositoryImpl implements EstateDataRepositoryCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] batchInsert(List<EstateData> data) {
        return this.jdbcTemplate.batchUpdate("insert into estate_data (id, data) values(?,?::jsonb) ON CONFLICT (id) DO NOTHING",
                new BatchPreparedStatementSetter() {
                    @SneakyThrows
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        var json = data.get(i).data.toString();
                        ps.setString(1, DigestUtils.md5DigestAsHex(json.getBytes()));
                        ps.setString(2, json);

                    }
                    public int getBatchSize() {
                        return data.size();
                    }
                });
    }
}
