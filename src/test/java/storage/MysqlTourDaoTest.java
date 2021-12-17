package storage;

import entity.Clients;
import entity.Hotel;
import entity.Tour;
import entity.Type_tour;
import exeption.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import storage.dao.TourDAO;
import storage.dao.Type_tourDAO;

import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MysqlTourDaoTest {
    private TourDAO tourDAO;

    public MysqlTourDaoTest() {
        DaoFactory.INSTANCE.testing();
        tourDAO = DaoFactory.INSTANCE.getTourDAO();
    }

    @Before
    public void setUp() throws Exception {

    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGetAll() {
        List<Tour> tour = tourDAO.getAll();
        assertTrue(tour.size() > 0);
        assertNotNull(tourDAO.getAll());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        tourDAO.getById(-1);

    }

    @Test
    public void save() {
        Date date = new Date(2000,10,10);
        Date date2 = new Date(2010,10,10);
        Date date3 = new Date(2011,10,10);

        Tour testTour = new Tour(date,date2);


        testTour = tourDAO.save(testTour);
        List<Tour> tourList = tourDAO.getAll();
        Tour savedTour = tourDAO.save(testTour);
        testTour.setId(savedTour.getId());
        assertEquals(testTour, savedTour);
        assertEquals(tourList.size() + 1, tourDAO.getAll().size());
        savedTour.setDate_end(date3);
        Tour savedTour1 = tourDAO.save(savedTour);
        assertEquals(savedTour1, savedTour);





    }


    @Test
    public void testDelete() {
    }
}