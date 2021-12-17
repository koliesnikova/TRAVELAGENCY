package storage;

import entity.Druh_jedla;
import entity.Type_tour;
import exeption.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.dao.Druh_jedlaDAO;
import storage.dao.Type_umiestneniaDAO;

import static org.junit.Assert.*;

public class MysqlDruh_jedlaTest {
    private Druh_jedlaDAO druh_jedlaDAO;
    public  MysqlDruh_jedlaTest(){
        DaoFactory.INSTANCE.testing();
        druh_jedlaDAO = DaoFactory.INSTANCE.getDruh_jedlaDAO();
    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        druh_jedlaDAO.getById(-1);


} @Test
    public void testUpdate (){
        Druh_jedla dj = druh_jedlaDAO.getById(1L);
        dj.setType("dfd");
        druh_jedlaDAO.save(dj);
        Druh_jedla up = druh_jedlaDAO.getById(1L);
        assertEquals(up.getType(),"dfd");
    }
    @Test
    public void testInsert(){

        Druh_jedla insert = druh_jedlaDAO.save(new Druh_jedla(1L,"TR"));
        Druh_jedla saved = druh_jedlaDAO.save(insert);
        assertNotNull(saved.getId());
        assertEquals(saved.getType(),insert.getType());
    }

}