package storage;

import entity.Type_tour;
import entity.Type_umiestnenia;
import exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import storage.dao.Type_umiestneniaDAO;

import java.sql.ResultSet;
import java.sql.SQLException;

public class MysqlType_umiestneniaDao implements Type_umiestneniaDAO {
    private JdbcTemplate jdbcTemplate;
    public MysqlType_umiestneniaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Type_umiestnenia getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM type_tour WHERE id =" + id, new MysqlType_umiestneniaDao.UmiestRowMapper());
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Type umiestania " + id + " not found");

        }
    }

    private class UmiestRowMapper implements RowMapper<Type_umiestnenia> {
        @Override
        public Type_umiestnenia mapRow(ResultSet rs, int rowNum) throws SQLException {
            long id = rs.getLong("id");
            String type = rs.getString("type_umiest");
            Boolean balkon = rs.getBoolean("balkon");
            Boolean vyh = rs.getBoolean("vyhladiakova_izba");
            return new Type_umiestnenia(id,type,balkon,vyh);
        }

    }
}
