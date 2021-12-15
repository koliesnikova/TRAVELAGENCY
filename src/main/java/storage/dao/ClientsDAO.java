package storage.dao;

import entity.Clients;
import exeption.EntityNotFoundException;
import exeption.EntityUndeletableException;

import java.util.List;

public interface ClientsDAO {
    List<Clients> getAll();
    Clients getById(long id) throws EntityNotFoundException;
    Clients save(Clients clients) throws EntityNotFoundException;
    Clients delete(long id) throws EntityNotFoundException, EntityUndeletableException;
}
