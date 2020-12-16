package com.saasestate.app.repository;

import com.saasestate.app.entity.Estate;
import lombok.SneakyThrows;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.BatchPreparedStatementSetter;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Repository;

import java.sql.PreparedStatement;
import java.sql.SQLException;
import java.util.List;

@Repository
public class EstateRepositoryImpl implements EstateRepositoryCustom {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    public int[] batchInsert(List<Estate> estates) {
        return this.jdbcTemplate.batchUpdate(
                "insert into estate (user_id, object_id, price, currency_type, data_hash, data) values(?,?,?,?,?,?::jsonb)" +
                        " ON CONFLICT ON CONSTRAINT idx_uoid DO UPDATE " +
                        " SET data_hash = excluded.data_hash," +
                        " data = excluded.data, " +
                        "     updated_at = now()",
                new BatchPreparedStatementSetter() {

                    @SneakyThrows
                    public void setValues(PreparedStatement ps, int i) throws SQLException {
                        ps.setInt(1, estates.get(i).getUserId());
                        ps.setInt(2, estates.get(i).getObjectId());
                        ps.setInt(3, estates.get(i).getPrice());
                        ps.setString(4, estates.get(i).getCurrency().type.toString());
                        ps.setInt(5, estates.get(i).data.hashCode());
                        ps.setString(6, estates.get(i).data.toString());
                    }

                    public int getBatchSize() {
                        return estates.size();
                    }
                });
    }
}
