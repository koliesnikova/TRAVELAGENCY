package sk.upjs.storage;

import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;
import org.springframework.dao.DataAccessException;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.jdbc.core.simple.SimpleJdbcInsert;
import sk.upjs.storage.dao.Type_umiestneniaDAO;


import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class MysqlType_umiestneniaDao implements Type_umiestneniaDAO {
    private JdbcTemplate jdbcTemplate;
    public MysqlType_umiestneniaDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public Type_umiestnenia getById(long id) throws EntityNotFoundException {
        try {
            return jdbcTemplate.queryForObject("SELECT * FROM type_umiestnenia WHERE id =" + id, new MysqlType_umiestneniaDao.UmiestRowMapper());
        } catch (DataAccessException e) {
            throw new EntityNotFoundException("Type umiestania " + id + " not found");

        }
    }
    @Override
    public List<Type_umiestnenia> getAll() {
        String sql = "select * from  type_umiestnenia ";
        return jdbcTemplate.query(sql, new MysqlType_umiestneniaDao.UmiestRowMapper() );
    }
    @Override
    public Type_umiestnenia save(Type_umiestnenia type_umiestnenia) throws EntityNotFoundException {
        if (type_umiestnenia == null) {
            return null;
        }
        if (type_umiestnenia.getId() == null) {  //insert
            SimpleJdbcInsert insert = new SimpleJdbcInsert(jdbcTemplate);
            insert.withTableName("type_umiestnenia");
            insert.usingGeneratedKeyColumns("id");
             insert.usingColumns("type_umiest","balkon","vyhladiakova_izba");
            Map<String, Object> valuesMap = new HashMap<>();
            valuesMap.put("type_umiest", type_umiestnenia.getType_umiest());
            valuesMap.put("balkon", type_umiestnenia.getBalkon());
            valuesMap.put("vyhladiakova_izba", type_umiestnenia.getVyhladiakova_izba());


            return new Type_umiestnenia(
                    insert.executeAndReturnKey(valuesMap).longValue(),
                    type_umiestnenia.getType_umiest(),
                    type_umiestnenia.getBalkon(),
                    type_umiestnenia.getVyhladiakova_izba()
            );
        } else { //update

            String sql = "UPDATE type_umiestnenia SET type_umiest =?, balkon=?,vyhladiakova_izba=? WHERE id = ?";

            int changed = jdbcTemplate.update(sql, type_umiestnenia.getType_umiest(),type_umiestnenia.getBalkon(),type_umiestnenia.getVyhladiakova_izba(), type_umiestnenia.getId());
            if (changed == 1) {
                return type_umiestnenia;
            } else {
                throw new EntityNotFoundException("Type umiest  with id " + type_umiestnenia.getId() + "not found");
            }
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
