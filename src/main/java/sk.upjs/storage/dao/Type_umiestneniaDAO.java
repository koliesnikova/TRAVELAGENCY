package sk.upjs.storage.dao;


import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;

import java.util.List;

public interface Type_umiestneniaDAO {
    Type_umiestnenia getById(long id) throws EntityNotFoundException;

    List<Type_umiestnenia> getAll();

    Type_umiestnenia save(Type_umiestnenia type_umiestnenia) throws EntityNotFoundException;
}
