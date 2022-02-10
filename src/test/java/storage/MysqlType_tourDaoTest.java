package storage;


import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;

import org.junit.jupiter.api.Test;
import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Type_tour;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.storage.DaoFactory;
import sk.upjs.storage.dao.Type_tourDAO;


import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

public class MysqlType_tourDaoTest {
    private Type_tourDAO type_tourDAO;
    public MysqlType_tourDaoTest(){
        DaoFactory.INSTANCE.testing();
        type_tourDAO = DaoFactory.INSTANCE.getType_tourDAO();
    }
    @BeforeEach
    public void setUp() throws Exception {
    }

    @AfterEach
    public void tearDown() throws Exception {
    }
   // @Test(expected = EntityNotFoundException.class)
    public void testGetById() {
        type_tourDAO.getById(-1);

    }
    @Test
    public void testGetAll() {
        List<Type_tour> type_tours = type_tourDAO.getAll();
        assertTrue(type_tours.size() > 0);
        assertNotNull(type_tourDAO.getAll());
    }

    @Test
    public void testUpdate (){
        Type_tour tt = type_tourDAO.getById(1L);
        tt.setType("dfd");
        type_tourDAO.save(tt);
        Type_tour up = type_tourDAO.getById(1L);
        assertEquals(up.getType(),"dfd");
    }
    @Test
    public void testInsert(){

        Type_tour insert = type_tourDAO.save(new Type_tour(1L,"TR"));
        Type_tour saved = type_tourDAO.save(insert);
        assertNotNull(saved.getId());
        assertEquals(saved.getType(),insert.getType());
    }



}