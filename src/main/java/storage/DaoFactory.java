package storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;
import storage.dao.ClientsDAO;
import storage.dao.HotelDAO;
import storage.dao.PredajDAO;
import storage.dao.TourDAO;

public enum DaoFactory {
    INSTANCE;

    private ClientsDAO clientsDAO;
    private HotelDAO hotelDAO;
    private PredajDAO predajDAO;
    private TourDAO tourDAO;


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
    public HotelDAO getHotelDAO(){
        if (hotelDAO == null) {
            hotelDAO = new MysqlHotelDao(getJdbcTemplate());
        }
        return hotelDAO;
    }

    public PredajDAO getPredajDAO(){
        if (predajDAO == null) {
            predajDAO = new MysqlPredajDao(getJdbcTemplate());
        }
        return predajDAO;

    }
    public TourDAO getTourDAO(){
        if(tourDAO == null) {
            tourDAO = new MysqlTourDao(getJdbcTemplate());
        }
        return tourDAO;
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
