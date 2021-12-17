package storage;

import entity.Druh_jedla;
import entity.Type_tour;
import exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import storage.dao.Druh_jedlaDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.Map;

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
    @Override
    public Druh_jedla save(Druh_jedla druh_jedla) throws EntityNotFoundException {
        if (druh_jedla == null) {
            return null;
        }
        if (druh_jedla.getId() == null) {  //insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("druh_jedla");
            insert.usingGeneratedKeyColumns("id");
            // insert.usingColumns("type");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("type", druh_jedla.getType());


            return new Druh_jedla(
                    insert.executeAndReturnKey(valuesMap).longValue(),
                    druh_jedla.getType()
            );
        } else { //update

            String sql = "UPDATE druh_jedla SET type = ? WHERE id = ?";

            int changed = jdbcTemplate.update(sql, druh_jedla.getType(), druh_jedla.getId());
            if (changed == 1) {
                return druh_jedla;
            } else {
                throw new EntityNotFoundException("Druh jedla tour with id " + druh_jedla.getId() + "not found");
            }
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
