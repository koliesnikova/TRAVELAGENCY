package storage.dao;


import entity.Type_umiestnenia;
import exeption.EntityNotFoundException;

public interface Type_umiestneniaDAO {
    Type_umiestnenia getById(long id) throws EntityNotFoundException;
}
