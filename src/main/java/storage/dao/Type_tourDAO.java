package storage.dao;


import entity.Type_tour;
import exeption.EntityNotFoundException;

public interface Type_tourDAO {
    Type_tour getById(long id) throws EntityNotFoundException;
}
