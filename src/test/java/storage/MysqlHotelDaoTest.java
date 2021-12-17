package storage;

import entity.Clients;
import entity.Hotel;
import exeption.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.dao.HotelDAO;

import java.util.List;

import static org.junit.Assert.*;

public class MysqlHotelDaoTest {
 private HotelDAO hotelDAO;

    public MysqlHotelDaoTest() {
        DaoFactory.INSTANCE.testing();
        hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test
    public void testGetAll() {
        List<Hotel> hotel = hotelDAO.getAllHotels();
        assertTrue(hotel.size() > 0);
        assertNotNull(hotelDAO.getAllHotels());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        hotelDAO.getById(-1);

    }

}