package storage;

import entity.Type_tour;
import exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import storage.dao.Type_tourDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

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
    @Override
    public List<Type_tour> getAll() {
        return jdbcTemplate.query("SELECT * FROM type_tour", new MysqlType_tourDao.TourRowMapper());

    }
    @Override
    public Type_tour save(Type_tour type_tour) throws EntityNotFoundException {
        if (type_tour == null) {
            return null;
        }
        if (type_tour.getId() == null) {  //insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("type_tour");
            insert.usingGeneratedKeyColumns("id");
           // insert.usingColumns("type");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("type", type_tour.getType());


            return new Type_tour(
                    insert.executeAndReturnKey(valuesMap).longValue(),
                    type_tour.getType()
            );
        } else { //update

            String sql = "UPDATE type_tour SET type = ? WHERE id = ?";

            int changed = jdbcTemplate.update(sql, type_tour.getType(), type_tour.getId());
            if (changed == 1) {
                return type_tour;
            } else {
                throw new EntityNotFoundException("Type tour with id " + type_tour.getId() + "not found");
            }
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
