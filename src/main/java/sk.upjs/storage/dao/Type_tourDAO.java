package sk.upjs.storage.dao;


import sk.upjs.entity.Type_tour;
import sk.upjs.exeption.EntityNotFoundException;

import java.util.List;

public interface Type_tourDAO {
    Type_tour getById(long id) throws EntityNotFoundException;

    //Type_tour idByName(String name) throws EntityNotFoundException;


    Type_tour idByName(String type) throws EntityNotFoundException;

    List<Type_tour> getAll();

    Type_tour save(Type_tour type_tour) throws EntityNotFoundException;
}
