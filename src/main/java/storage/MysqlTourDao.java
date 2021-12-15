package storage;

import org.springframework.jdbc.core.JdbcTemplate;
import storage.dao.PredajDAO;

public class MysqlTourDao implements PredajDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlTourDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
