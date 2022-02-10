package storage;


import org.junit.jupiter.api.Test;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.Type_umiestneniaDAO;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;


public class MysqlType_umiestneniaDaoTest {

    private Type_umiestneniaDAO type_umiestneniaDAO;
    public  MysqlType_umiestneniaDaoTest(){
        DaoFactory.INSTANCE.testing();
        type_umiestneniaDAO = DaoFactory.INSTANCE.getType_umiestneniaDAO();
    }

  //  @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        type_umiestneniaDAO.getById(-1);

    }
    @Test
    public void testGetAll() {
        List<Type_umiestnenia> type_umiestnenias = type_umiestneniaDAO.getAll();
        assertTrue(type_umiestnenias.size() > 0);
        assertNotNull(type_umiestneniaDAO.getAll());
    }




    @Test
    public void testUpdate (){
        Type_umiestnenia tt = type_umiestneniaDAO.getById(1L);
        tt.setType_umiest("dfd");
        tt.setVyhladiakova_izba(true);
        tt.setBalkon(true);
        type_umiestneniaDAO.save(tt);
        Type_umiestnenia up = type_umiestneniaDAO.getById(1L);
        assertEquals(up.getType_umiest(),"dfd");
    }
    @Test
    public void testInsert(){

        Type_umiestnenia insert = type_umiestneniaDAO.save(new Type_umiestnenia(1L,"TR",true,true));
        Type_umiestnenia saved = type_umiestneniaDAO.save(insert);
        assertNotNull(saved.getId());
        assertEquals(saved.getType_umiest(),insert.getType_umiest());
    }
}