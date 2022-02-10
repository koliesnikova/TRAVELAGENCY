package sk.upjs.storage;

import sk.upjs.entity.Hotel;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.dao.DuplicateKeyException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.storage.dao.HotelDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlHotelDao implements HotelDAO {
    private JdbcTemplate jdbcTemplate;
    public MysqlHotelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
  @Override
    public List<Hotel> getAllHotels() {
        String sql = "select * from  hotel ";
        return jdbcTemplate.query(sql, new HotelRowMapper() );
    }

    public Hotel getById(long id) throws EntityNotFoundException {

        try {
            String sql = " select * from  hotel where id=";
            return jdbcTemplate.queryForObject(sql+ id, new HotelRowMapper() );
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Hotel " + id + " not found");

        }
    }

    @Override
    public Hotel save(Hotel hotel) throws EntityNotFoundException {
        if (hotel == null) {
            return null;
        }
        if (hotel.getId() == null) {  //insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("hotel");
            insert.usingGeneratedKeyColumns("id");
            insert.usingColumns("hotel_name","stars","type_umiestnenia","price","krajina","mesto");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("hotel_name", hotel.getHotel_name());
            valuesMap.put("stars",hotel.getStars());
            valuesMap.put("type_umiestnenia",hotel.getType_umiestnenia().getId());
            valuesMap.put("price", hotel.getPrice());
            valuesMap.put("krajina", hotel.getKrajina());
            valuesMap.put("mesto", hotel.getMesto());
            Long id = null;
            try {  id = insert.executeAndReturnKey(valuesMap).longValue();} catch (DuplicateKeyException e) {}

            return new Hotel(
                    id,
                    hotel.getHotel_name(),
                    hotel.getStars(),
                    hotel.getType_umiestnenia(),
                    hotel.getPrice(),
                    hotel.getKrajina(),
                    hotel.getMesto()
            );
        } else { //update

            String sql = "UPDATE hotel SET hotel_name = ?,stars=?,type_umiestnenia=?,price=?,krajina=?,mesto=?  WHERE id = ?";

            int changed = jdbcTemplate.update(sql,hotel.getHotel_name(),hotel.getStars(),hotel.getType_umiestnenia().getId(),hotel.getPrice(),hotel.getKrajina(),hotel.getMesto(),hotel.getId());
            if (changed == 1) {
                return hotel;
            } else {
                throw new EntityNotFoundException("Hotel with id " + hotel.getId() + "not found");
            }
        }
    }

    @Override
    public Hotel idByName(String hotel_name) {
        //try {
            String sql = "SELECT id FROM hotel WHERE hotel_name =?";
            return jdbcTemplate.queryForObject( sql, new TourRowMapper2(),hotel_name);
     //   } catch (DataAccessException e) {
       //     throw new EntityNotFoundException("empty for refresh");

        //}
    }

    private class HotelRowMapper implements RowMapper<Hotel> {
        @Override
        public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hotel hotel = new Hotel();
            hotel.setId(rs.getLong("id"));
            hotel.setHotel_name(rs.getString("hotel_name"));
            hotel.setStars(rs.getInt("stars"));
            hotel.setPrice(rs.getFloat("price"));
            hotel.setKrajina(rs.getString("krajina"));
            hotel.setMesto(rs.getString("mesto"));
            hotel.setType_umiestnenia(DaoFactory.INSTANCE.getType_umiestneniaDAO().getById(rs.getLong("type_umiestnenia")));
            return hotel;
        }

    }
    private class TourRowMapper2 implements RowMapper<Hotel> {
        @Override
        public Hotel mapRow(ResultSet rs, int rowNum) throws SQLException {
            Hotel hotel = new Hotel();
            hotel.setId(rs.getLong("id"));
            return hotel;
        }

    }
}
