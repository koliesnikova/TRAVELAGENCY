package sk.upjs.storage;

import org.springframework.jdbc.core.JdbcTemplate;

import com.mysql.cj.jdbc.MysqlDataSource;
import sk.upjs.storage.dao.ClientsDAO;
import sk.upjs.storage.dao.Druh_jedlaDAO;
import sk.upjs.storage.dao.HotelDAO;
import sk.upjs.storage.dao.PredajDAO;
import sk.upjs.storage.dao.TourDAO;
import sk.upjs.storage.dao.Type_tourDAO;
import sk.upjs.storage.dao.Type_umiestneniaDAO;


public enum DaoFactory {
    INSTANCE;

    private ClientsDAO clientsDAO;

    private PredajDAO predajDAO;
    private TourDAO tourDAO;
    private HotelDAO hotelDAO;
    private Druh_jedlaDAO druhjedlaDAO;
    private Type_umiestneniaDAO type_umiestneniaDAO;
    private Type_tourDAO type_tourDAO;

    private boolean testing = false;

    public void testing() {
        testing = true;
    }

    public ClientsDAO getClientsDAO() {
        if (clientsDAO == null) {
            clientsDAO = new MysqlClientsDao(getJdbcTemplate());
        }
        return clientsDAO;
    }

    public PredajDAO getPredajDAO() {
        if (predajDAO == null) {
            predajDAO = new MysqlPredajDao(getJdbcTemplate());
        }
        return predajDAO;

    }

    public TourDAO getTourDAO() {
        if (tourDAO == null) {
            tourDAO = new MysqlTourDao(getJdbcTemplate());
        }
        return tourDAO;
    }
    public Druh_jedlaDAO getDruh_jedlaDAO(){
        if (druhjedlaDAO==null){
            druhjedlaDAO = new MysqlDruh_jedlaDao(getJdbcTemplate());
        }
        return druhjedlaDAO;
    }
    public HotelDAO getHotelDAO(){
        if (hotelDAO == null){
            hotelDAO = new MysqlHotelDao(getJdbcTemplate());
        }
        return hotelDAO;
    }
    public Type_tourDAO getType_tourDAO(){
        if (type_tourDAO == null) {
            type_tourDAO = new MysqlType_tourDao(getJdbcTemplate());
        }
        return type_tourDAO;
    }
    public Type_umiestneniaDAO getType_umiestneniaDAO(){
        if (type_umiestneniaDAO == null){
            type_umiestneniaDAO = new MysqlType_umiestneniaDao(getJdbcTemplate());

        }
        return type_umiestneniaDAO;
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
