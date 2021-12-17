package storage.dao;


import entity.Type_tour;
import exeption.EntityNotFoundException;

import java.util.List;

public interface Type_tourDAO {
    Type_tour getById(long id) throws EntityNotFoundException;

    List<Type_tour> getAll();

    Type_tour save(Type_tour type_tour) throws EntityNotFoundException;
}
