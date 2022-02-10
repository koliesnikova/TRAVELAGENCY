package sk.upjs.storage;

import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Tour;

import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.storage.dao.TourDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlTourDao implements TourDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlTourDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<Tour> getAll() {
        String sql = "select * from  tour ";
        //String sql = "select t.id, t.date_begin,t.date_end, tt.type 'type_tour', d.type 'druh_jedla', h.hotel_name from  tour as t join type_tour as tt  on t.type_tour=tt.id join druh_jedla as d on t.druh_jedla= d.id  join hotel as h on t.hotel = h.id ORDER BY t.id";
        return jdbcTemplate.query(sql, new TourRowMapper());
    }

    @Override
    public Tour getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM tour WHERE id =" + id, new TourRowMapper());
        } catch (DataAccessException e) {    
            throw new EntityNotFoundException("Tour with id " + id + " not found");

        }
    }

    @Override
    public Tour save(Tour tour) throws EntityNotFoundException {
        if (tour == null) {
            return null;
        }
        if (tour.getId() == null) { //insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("tour");
            insert.usingGeneratedKeyColumns("id");
            insert.usingColumns("type_tour", "date_begin", "date_end", "druh_jedla", "hotel");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("type_tour", tour.getType_tour().getId());
            valuesMap.put("date_begin", tour.getDate_begin());
            valuesMap.put("date_end", tour.getDate_end());
            valuesMap.put("druh_jedla", tour.getDruh_jedla().getId());
            valuesMap.put("hotel", tour.getHotel().getId());
            return new Tour(
                    insert.executeAndReturnKey(valuesMap).longValue(),
                    tour.getType_tour(),
                    tour.getDate_begin(),
                    tour.getDate_end(),
                    tour.getDruh_jedla(),
                    tour.getHotel()
            );
        } else { //update
            String sql = "UPDATE tour SET type_tour=?,date_begin=?,date_end=?, druh_jedla=?,hotel=? WHERE id=?";
            int changed = jdbcTemplate.update(sql, tour.getType_tour().getId(), tour.getDate_begin(), tour.getDate_end(), tour.getDruh_jedla().getId(), tour.getHotel().getId(),tour.getId());
            if (changed == 1) {
                return tour;
            } else {
                throw new EntityNotFoundException("Tour with id " + tour.getId() + "not found");
            }

        }

    }

    public void deleteAllByDruhJedla (Druh_jedla druh_jedla){
        try {
            String sql = "DELETE FROM druh_jedla WHERE type = " +druh_jedla.getId();
            jdbcTemplate.update(sql);
        }
        catch(Exception e) {

        }
    }

    @Override
    public Tour idByName(Long s1, String date) {
        String sql = "SELECT id FROM tour WHERE type_tour =? and date_begin=?";
        return jdbcTemplate.queryForObject( sql, new TourRowMapper2(),s1,date);
    }

    private class TourRowMapper implements RowMapper<Tour> {
        @Override
        public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tour tour = new Tour();
            tour.setId(rs.getLong("id"));
            tour.setType_tour(DaoFactory.INSTANCE.getType_tourDAO().getById(rs.getLong("type_tour")));
            tour.setDate_begin(rs.getDate("date_begin"));
            tour.setDate_end(rs.getDate("date_end"));
            tour.setDruh_jedla(DaoFactory.INSTANCE.getDruh_jedlaDAO().getById(rs.getLong("druh_jedla")));
            tour.setHotel(DaoFactory.INSTANCE.getHotelDAO().getById(rs.getLong("hotel")));
            return tour;

        }
    }
    private class TourRowMapper2 implements RowMapper<Tour> {
        @Override
        public Tour mapRow(ResultSet rs, int rowNum) throws SQLException {
            Tour tour = new Tour();
            tour.setId(rs.getLong("id"));

            return tour;

        }
    }

    @Override
    public Tour delete(long id) throws EntityNotFoundException, EntityUndeletableException {
        Tour tour = getById(id);
        try {
            jdbcTemplate.update("DELETE FROM tour WHERE id=" + id);
        } catch (DataIntegrityViolationException e) {
            throw new EntityUndeletableException(
                    "Tour " + tour + " is a part of some predaj, cannot be deleted", e);
        }
        return tour;
    }
}


