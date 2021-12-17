package storage;

import exeption.EntityNotFoundException;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import storage.dao.Type_umiestneniaDAO;

public class MysqlType_umiestneniaDaoTest {

    private Type_umiestneniaDAO type_umiestneniaDAO;
    public  MysqlType_umiestneniaDaoTest(){
        DaoFactory.INSTANCE.testing();
        type_umiestneniaDAO = DaoFactory.INSTANCE.getType_umiestneniaDAO();
    }
    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }
    @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        type_umiestneniaDAO.getById(-1);

    }
}