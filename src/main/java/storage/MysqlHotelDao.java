package storage;

import org.springframework.jdbc.core.JdbcTemplate;
import storage.dao.HotelDAO;

public class MysqlHotelDao implements HotelDAO {
    private JdbcTemplate jdbcTemplate;

    public MysqlHotelDao(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }
}
