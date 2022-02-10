package sk.upjs.storage;

import sk.upjs.entity.Druh_jedla;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.storage.dao.Druh_jedlaDAO;
import sk.upjs.storage.dao.TourDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
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
    public List<Druh_jedla> getAll() {
        String sql = "select * from  druh_jedla ";
        return jdbcTemplate.query(sql, new DruhRowMapper());
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

    @Override
    public Druh_jedla idByName(String type) {
        try {
            String sql = "SELECT * FROM druh_jedla WHERE type =?";
            return jdbcTemplate.queryForObject( sql, new TourRowMapper2(),type);
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("empty for refresh");

        }
    }

    public Druh_jedla delete(long id) throws EntityNotFoundException, EntityUndeletableException {
       Druh_jedla druh_jedla = getById(id);
        TourDAO tourDAO = DaoFactory.INSTANCE.getTourDAO();
        tourDAO.deleteAllByDruhJedla(druh_jedla);

        try {

            jdbcTemplate.update("DELETE FROM druh_jedla WHERE id = " + id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityUndeletableException(
                    "Druh jedla " + druh_jedla + " is a part of some tour, cannot be deleted", e);
        }
        return druh_jedla;
    }

    private class DruhRowMapper implements RowMapper<Druh_jedla> {
        @Override
        public Druh_jedla mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String type = rs.getString("type");
            return new Druh_jedla(id, type);
        }

    }
    private class TourRowMapper2 implements RowMapper<Druh_jedla> {
        @Override
        public Druh_jedla mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");

            return new Druh_jedla(id);
        }

    }
}
