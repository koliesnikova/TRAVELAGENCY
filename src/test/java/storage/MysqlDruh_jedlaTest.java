package storage;

import org.junit.jupiter.api.Test;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Type_tour;
import sk.upjs.exeption.EntityNotFoundException;

import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.Druh_jedlaDAO;

import java.util.List;


import static org.junit.jupiter.api.Assertions.*;

public class MysqlDruh_jedlaTest {
    private Druh_jedlaDAO druh_jedlaDAO;
    public  MysqlDruh_jedlaTest(){
        DaoFactory.INSTANCE.testing();
        druh_jedlaDAO = DaoFactory.INSTANCE.getDruh_jedlaDAO();
    }

  //  @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
      //  druh_jedlaDAO.getById(-1);


}
@Test
    public void testUpdate (){
        Druh_jedla dj = druh_jedlaDAO.getById(1L);
        dj.setType("dfd");
        druh_jedlaDAO.save(dj);
        Druh_jedla up = druh_jedlaDAO.getById(1L);
        assertEquals(up.getType(),"dfd");
    }
    @Test
    public void testGetAll() {
        List<Druh_jedla> druh_jedlas = druh_jedlaDAO.getAll();
        assertTrue(druh_jedlas.size() > 0);
        assertNotNull(druh_jedlaDAO.getAll());
    }

    @Test
    public void testInsert(){

        Druh_jedla insert = druh_jedlaDAO.save(new Druh_jedla(1L,"TR"));
        Druh_jedla saved = druh_jedlaDAO.save(insert);
        assertNotNull(saved.getId());
        assertEquals(saved.getType(),insert.getType());
    }

}