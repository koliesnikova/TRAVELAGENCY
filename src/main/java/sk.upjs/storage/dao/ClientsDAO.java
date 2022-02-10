package sk.upjs.storage.dao;

import sk.upjs.entity.Clients;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;

import java.util.List;

public interface ClientsDAO {
    List<Clients> getAll();
    Clients getById(long id) throws EntityNotFoundException;
    Clients save(Clients clients) throws EntityNotFoundException;
    Clients delete(long id) throws EntityNotFoundException, EntityUndeletableException;
}
