package sk.upjs.storage.dao;

import sk.upjs.entity.Clients;
import sk.upjs.entity.Predaj;
import sk.upjs.entity.Tour;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;

import java.util.List;

public interface PredajDAO {


    List<Predaj> getAll();

    Predaj getById(long id) throws EntityNotFoundException;

    Predaj save(Predaj predaj) throws EntityNotFoundException;

    Predaj delete(long id) throws EntityNotFoundException, EntityUndeletableException;

    void deleteAllByClient (Clients clients);
}
