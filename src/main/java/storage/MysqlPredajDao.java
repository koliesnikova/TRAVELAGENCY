package storage;

import entity.Clients;
import entity.Predaj;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import storage.dao.PredajDAO;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;

public class MysqlPredajDao implements PredajDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlPredajDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    public List<Predaj> getAll() {
        String sql = "";
        return jdbcTemplate.query(sql, new MysqlPredajDao.PredajRowMapper());

    }
    private class PredajRowMapper implements RowMapper<Predaj> {
        @Override
        public Predaj mapRow(ResultSet rs, int rowNum) throws SQLException {
            Predaj predaj = new Predaj();
            predaj.setId(rs.getLong("id"));
            predaj.setClient(DaoFactory.INSTANCE.getClientsDAO().getById(rs.getLong("client")));
            predaj.setDate(rs.getDate("date"));
            predaj.setPrice(rs.getFloat("price"));
        //    predaj.setTour(DaoFactory.INSTANCE.getTourDAO().getById(rs.getLong("tour")));

            return null;
        }
}}
