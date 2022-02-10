package storage;



import org.junit.jupiter.api.Test;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.HotelDAO;
import sk.upjs.storage.dao.Type_umiestneniaDAO;
import sk.upjs.storage.dao.HotelDAO;
import sk.upjs.storage.dao.Type_umiestneniaDAO;

import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MysqlHotelDaoTest {
 private HotelDAO hotelDAO;
 private Type_umiestneniaDAO type_umiestneniaDAO;
 private Type_umiestnenia tu = new Type_umiestnenia(8L,"sdfs", true,true);
    private Hotel testHotel = new Hotel("ytfds",7,tu,567F,"sdf","sff") ;
    public MysqlHotelDaoTest() {
        DaoFactory.INSTANCE.testing();
        hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
        type_umiestneniaDAO = DaoFactory.INSTANCE.getType_umiestneniaDAO();
    }

    @Test
    public void testGetAll() {
        List<Hotel> hotel = hotelDAO.getAllHotels();
        assertTrue(hotel.size() > 0);
        assertNotNull(hotelDAO.getAllHotels());
    }

   // @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        hotelDAO.getById(-1);

    }

    @Test
    public void testUpdate() {
        Hotel hotel = hotelDAO.getById(1L);
        hotel.setHotel_name("TEST");
        hotelDAO.save(hotel);
        Hotel updated = hotelDAO.getById(1L);

        assertEquals(updated.getHotel_name(),"TEST");
    }

    @Test
    public void save() {
        List<Hotel> userList = hotelDAO.getAllHotels();

        Hotel savedHotel = hotelDAO.save(testHotel);
        testHotel.setId(savedHotel.getId());
        assertEquals(testHotel.getId(), savedHotel.getId());
        assertEquals(userList.size() + 1, hotelDAO.getAllHotels().size());
        savedHotel.setMesto("testuuuu");
        Hotel savedUser1 = hotelDAO.save(savedHotel);
        assertEquals(savedUser1, savedHotel);
    }



}