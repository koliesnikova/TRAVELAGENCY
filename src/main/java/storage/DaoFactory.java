package storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;

public enum DaoFactory {
    INSTANCE;

    private ClientsDAO clientsDAO;
    private boolean testing =   false;

    public void testing() {
        testing = true;
    }

    public ClientsDAO getClientsDAO() {
        if (clientsDAO == null) {
            clientsDAO = new MysqlClientsDao(getJdbcTemplate());
        }
        return clientsDAO;
    }

    private JdbcTemplate jdbcTemplate;

    private JdbcTemplate getJdbcTemplate() {
        if (jdbcTemplate == null) {
            MysqlDataSource dataSource = new MysqlDataSource();
            dataSource.setUser("paz");
            dataSource.setPassword("Ilovepaz1c");
            if (testing) {
                dataSource.setURL("jdbc:mysql://localhost/travelagency_test?serverTimezone=Europe/Bratislava");
            } else
                dataSource.setURL("jdbc:mysql://localhost/travelagency?serverTimezone=Europe/Bratislava");
            jdbcTemplate = new JdbcTemplate(dataSource);
        }
        return jdbcTemplate;
    }

}
