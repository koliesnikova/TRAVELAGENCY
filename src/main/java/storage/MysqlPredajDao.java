package storage;

import org.springframework.jdbc.core.JdbcTemplate;
import storage.dao.PredajDAO;

public class MysqlPredajDao implements PredajDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlPredajDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
