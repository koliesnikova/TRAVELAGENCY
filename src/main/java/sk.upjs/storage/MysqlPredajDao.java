package sk.upjs.storage;

import sk.upjs.entity.Clients;
import sk.upjs.entity.Predaj;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.storage.dao.PredajDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlPredajDao implements PredajDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlPredajDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Predaj> getAll() {
        String sql = "SELECT * FROM predaj";
        return jdbcTemplate.query(sql, new MysqlPredajDao.PredajRowMapper());

    }

    @Override
    public Predaj getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM predaj WHERE id =" + id, new PredajRowMapper());
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Predaj with id " + id + " not found");

        }
    }

    @Override
    public Predaj save(Predaj predaj) throws EntityNotFoundException {
        if (predaj == null) {
            return null;
        }
        if (predaj.getId() == null) { //insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("predaj");
            insert.usingGeneratedKeyColumns("id");
            insert.usingColumns("client", "date", "price", "tour");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("client", predaj.getClient().getId());
            valuesMap.put("date", predaj.getDate());
            valuesMap.put("price", predaj.getPrice());
            valuesMap.put("tour", predaj.getTour().getId());
            return new Predaj(
                    insert.executeAndReturnKey(valuesMap).longValue(),
                    predaj.getClient(),
                    predaj.getDate(),
                    predaj.getPrice(),
                    predaj.getTour()
            );
        } else { //update
            String sql = "UPDATE predaj SET client=?,date=?,price=?,tour=? WHERE id = ?";
            int changed = jdbcTemplate.update(sql, predaj.getClient().getId(), predaj.getDate(), predaj.getPrice(), predaj.getTour().getId(),predaj.getId());
            if (changed == 1) {
                return predaj;
            } else {
                throw new EntityNotFoundException("Predaj with id " + predaj.getId() + "not found");
            }
        }
    }

    @Override
    public Predaj delete(long id) throws EntityNotFoundException, EntityUndeletableException {
        Predaj predaj = getById(id);

        try {
            jdbcTemplate.update("DELETE FROM predaj WHERE id=" + id);

        } catch (DataIntegrityViolationException e) {
            throw new EntityUndeletableException(
                    "Predaj " + predaj + " is a part of some predaj, cannot be deleted", e);
        }
        return predaj;
    }
    @Override
   public void deleteAllByClient (Clients clients){
        try {
         String sql = "DELETE FROM predaj WHERE client = " +clients.getId();
         jdbcTemplate.update(sql);
        }
        catch(Exception e) {

        }
    }

    private class PredajRowMapper implements RowMapper<Predaj> {
        @Override
        public Predaj mapRow(ResultSet rs, int rowNum) throws SQLException {
            Predaj predaj = new Predaj();
            predaj.setId(rs.getLong("id"));
            predaj.setClient(DaoFactory.INSTANCE.getClientsDAO().getById(rs.getLong("client")));
            predaj.setDate(rs.getDate("date"));
            predaj.setPrice(rs.getFloat("price"));
            predaj.setTour(DaoFactory.INSTANCE.getTourDAO().getById(rs.getLong("tour")));

            return predaj;
        }
    }
}
