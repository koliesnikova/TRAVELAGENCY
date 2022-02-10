package storage;

import org.junit.jupiter.api.Test;
import sk.upjs.exeption.EntityNotFoundException;

import sk.upjs.entity.Clients;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Predaj;
import sk.upjs.entity.Tour;
import sk.upjs.entity.Type_tour;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityUndeletableException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.PredajDAO;

import java.util.Date;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MysqlPredajDaoTest {

    private PredajDAO predajDAO;
    Date date2 = new Date(2010, 10, 10);
    Date date = new Date(2000, 10, 10);
    Clients cl = new Clients("dfd","ddf");
    private Type_umiestnenia tu = new Type_umiestnenia(8L, "sdfs", true, true);
    private Hotel testHotel = new Hotel(12L, "ytfds", 7, tu, 567F, "sdf", "sff");
    private Type_tour tt = new Type_tour(6L, "TEST");
    private Druh_jedla dj = new Druh_jedla(7L,"dsk");
    private Tour testTour = new Tour(tt, date, date2, dj, testHotel);
   private Predaj testPredaj = new Predaj(cl,date,675F,testTour);
    public MysqlPredajDaoTest() {
        DaoFactory.INSTANCE.testing();
        predajDAO = DaoFactory.INSTANCE.getPredajDAO();
    }




    @Test
    public void testGetAll() {
        List<Predaj> tour = predajDAO.getAll();
        assertTrue(tour.size() > 0);
        assertNotNull(predajDAO.getAll());
    }

  //  @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        predajDAO.getById(-1);

    }

    @Test
    public void save() {
        List<Predaj> predajList = predajDAO.getAll();
        testPredaj = predajList.get(predajList.size() - 1);
        testPredaj.setId(6L);
        Predaj savedPredaj = predajDAO.save(testPredaj);
        testPredaj.setId(savedPredaj.getId());
        assertEquals(testPredaj.getId(), savedPredaj.getId());
        assertEquals(predajList.size() , predajDAO.getAll().size());
        savedPredaj.setPrice(64F);
        Predaj savedUser1 = predajDAO.save(testPredaj);
        assertEquals(savedUser1, savedPredaj);
    }

    @Test
    public void delete() throws EntityUndeletableException {
        List<Predaj> tourList = predajDAO.getAll();
        testPredaj = tourList.get(tourList.size() - 1);
        testPredaj.setId(null);
        Predaj savedTour = predajDAO.save(testPredaj);
        int aftersave = predajDAO.getAll().size();
        predajDAO.delete(savedTour.getId());
        int afterdelete = tourList.size();
        assertEquals(afterdelete,aftersave-1);



    }
}