package storage;



import org.junit.jupiter.api.Test;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Tour;
import sk.upjs.entity.Type_tour;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.TourDAO;


import java.util.Date;
import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class MysqlTourDaoTest {
    private TourDAO tourDAO;
    Date date = new Date(2000, 10, 10);
    Date date2 = new Date(2010, 10, 10);
    Date date3 = new Date(2020, 10, 10);

    private Type_umiestnenia tu = new Type_umiestnenia(8L, "sdfs", true, true);
    private Hotel testHotel = new Hotel(12L, "ytfds", 7, tu, 567F, "sdf", "sff");
    private Type_tour tt = new Type_tour(6L, "TEST");
    private Druh_jedla dj = new Druh_jedla(5L,"dsk");
    private Tour testTour = new Tour(tt, date, date2, dj, testHotel);

    public MysqlTourDaoTest() {
        DaoFactory.INSTANCE.testing();
        tourDAO = DaoFactory.INSTANCE.getTourDAO();
    }



    @Test
    public void testGetAll() {
        List<Tour> tour = tourDAO.getAll();
        assertTrue(tour.size() > 0);
        assertNotNull(tourDAO.getAll());
    }

  //  @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        tourDAO.getById(-1);

    }




    @Test
    public void save() {
        List<Tour> tourList = tourDAO.getAll();
        testTour = tourList.get(tourList.size() - 1);
        testTour.setId(12L);
        Tour savedTour = tourDAO.save(testTour);
        testTour.setId(savedTour.getId());
        assertEquals(testTour.getId(), savedTour.getId());
        assertEquals(tourList.size() , tourDAO.getAll().size());
        savedTour.setDate_end(date3);
        Tour savedUser1 = tourDAO.save(savedTour);
        assertEquals(savedUser1, savedTour);
    }

    @Test
    public void delete() throws EntityUndeletableException {
        List<Tour> tourList = tourDAO.getAll();
        testTour = tourList.get(tourList.size() - 1);
        testTour.setId(null);
        Tour savedTour = tourDAO.save(testTour);

       int aftersave = tourDAO.getAll().size();
       tourDAO.delete(savedTour.getId());
       int afterdelete = tourList.size();
       assertEquals(afterdelete,aftersave-1);



    }


}