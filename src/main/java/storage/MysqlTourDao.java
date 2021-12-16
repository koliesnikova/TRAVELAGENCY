package storage;

import org.springframework.jdbc.core.JdbcTemplate;
import storage.dao.PredajDAO;
import storage.dao.TourDAO;

public class MysqlTourDao implements TourDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlTourDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
