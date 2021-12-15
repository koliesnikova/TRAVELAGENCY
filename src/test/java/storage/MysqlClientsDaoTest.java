package storage;

import entity.Clients;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;

import java.util.IllegalFormatException;
import java.util.List;

import static org.junit.Assert.*;

public class MysqlClientsDaoTest {
    private ClientsDAO clientsDAO;

    public MysqlClientsDaoTest() {
        DaoFactory.INSTANCE.testing();
        clientsDAO = DaoFactory.INSTANCE.getClientsDAO();
    }

    @Before
    public void setUp() throws Exception {
    }

    @After
    public void tearDown() throws Exception {
    }

    @Test
     public void testGetAll() {
        List<Clients> clients = clientsDAO.getAll();
        assertTrue(clients.size()>0);
    }
    @Test(expected = EntityNotFoundException.class)
    public void testGetById(){
        clientsDAO.getById(-1);

    }

}