package storage;

import entity.Druh_jedla;
import entity.Type_tour;
import exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import storage.dao.Type_tourDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlType_tourDao implements Type_tourDAO {
    private final JdbcTemplate jdbcTemplate;

    public MysqlType_tourDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Type_tour getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT type,id FROM type_tour WHERE id =" + id, new MysqlType_tourDao.TourRowMapper());
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Type tour " + id + " not found");

        }
    }

    private class TourRowMapper implements RowMapper<Type_tour> {
        @Override
        public Type_tour mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String type = rs.getString("type");
            return new Type_tour(id, type);
        }

    }
}
