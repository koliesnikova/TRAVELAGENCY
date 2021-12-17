package storage;

import entity.Predaj;
import entity.Tour;
import exeption.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.dao.PredajDAO;
import storage.dao.TourDAO;

import java.util.List;

import static org.junit.Assert.*;

public class MysqlPredajDaoTest {

    private PredajDAO predajDAO;

    public MysqlPredajDaoTest() {
        DaoFactory.INSTANCE.testing();
        predajDAO = DaoFactory.INSTANCE.getPredajDAO();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }


    @Test
    public void testGetAll() {
        List<Predaj> tour = predajDAO.getAll();
        assertTrue(tour.size() > 0);
        assertNotNull(predajDAO.getAll());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        predajDAO.getById(-1);

    }

    @Test
    public void testUpdate() {

    }

    @Test
    public void testInsert() {

    }

    @Test
    public void testDelete() {
    }
}