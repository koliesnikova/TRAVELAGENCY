package storage;

import entity.Clients;
import exeption.EntityNotFoundException;
import exeption.EntityUndeletableException;
import org.junit.Test;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import storage.dao.ClientsDAO;
import java.util.Date;
import java.util.List;

import static org.junit.Assert.*;

public class MysqlClientsDaoTest {
    private ClientsDAO clientsDAO;
    private Clients savedClient;

    public MysqlClientsDaoTest() {
        DaoFactory.INSTANCE.testing();
        clientsDAO = DaoFactory.INSTANCE.getClientsDAO();
    }

    @BeforeEach
    public void setUp() throws Exception {

        Clients testClient = new Clients("Priezvisko", "542242");
        testClient = clientsDAO.save(testClient);
    }


    @AfterEach
    public void tearDown() throws Exception {
        Clients testClient = new Clients("Meno", "Priezvisko");
        testClient = clientsDAO.save(testClient);
        clientsDAO.delete(testClient.getId());
    }

    @Test
    public void testGetAll() {
        List<Clients> clients = clientsDAO.getAll();
        assertTrue(clients.size() > 0);
        assertNotNull(clientsDAO.getAll());
    }

    @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        clientsDAO.getById(-1);

    }

    @Test
   public void testUpdate() {
      Clients clients = clientsDAO.getById(1L);
      clients.setMeno("test");
      clientsDAO.save(clients);
      Clients updated = clientsDAO.getById(1L);
      assertEquals(updated.getMeno(),"test");
    }

    @Test
    public void testInsert(){
        Date date = new Date(2000,10,10);
        Clients insert = clientsDAO.save(new Clients(12L,"Inserted","Inserted",date,"moscow","435353"));
        assertNotNull(insert);
    }

    //Subject subject = new Subject();
    //		subject.setName("example");
    //		Long id = dao.save(subject).getId();
    //		subject.setId(id);
    //		int afterSave = dao.getAllSubjects().size();
    //		dao.remove(subject);
    //		int afterDelete = dao.getAllSubjects().size();
    //		assertTrue(afterDelete == afterSave - 1);
    @Test
    public void testDelete() {
        Date date = new Date(2000,10,10);
        Clients clients= new Clients(5L,"Inserted","Inserted",date,"moscow","435353");

        Long id = clientsDAO.save(clients).getId();
        clients.setId(id);
        int afterSave = clientsDAO.getAll().size();
        try {
            clientsDAO.delete(id);
        } catch (EntityUndeletableException e) {
            e.printStackTrace();
        }
        int afterDelete = clientsDAO.getAll().size();
        assertTrue(afterDelete==afterSave-1);
    }

    //@Test
    //public void testDelete() {
    //    Clients clients = clientsDAO.getById(3L);
     //   try {
    //        clientsDAO.delete(3L);
     //   } catch (EntityUndeletableException e) {
     //       e.printStackTrace();
     //   }
     //   Clients deleted = clientsDAO.getById(3L);
      //  assertNull(deleted);



    }





