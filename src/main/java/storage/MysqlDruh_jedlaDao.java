package storage;

import entity.Druh_jedla;
import exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import storage.dao.Druh_jedlaDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlDruh_jedlaDao implements Druh_jedlaDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlDruh_jedlaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Druh_jedla getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT type,id FROM druh_jedla WHERE id =" + id, new DruhRowMapper());
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Food type " + id + " not found");

        }

    }

    private class DruhRowMapper implements RowMapper<Druh_jedla> {
        @Override
        public Druh_jedla mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String type = rs.getString("type");
            return new Druh_jedla(id, type);
        }

    }
}
