package storage.dao;

import entity.Clients;
import entity.Predaj;
import entity.Tour;
import exeption.EntityNotFoundException;
import exeption.EntityUndeletableException;

import java.util.List;

public interface PredajDAO {


    List<Predaj> getAll();

    Predaj getById(long id) throws EntityNotFoundException;

    Predaj save(Predaj predaj) throws EntityNotFoundException;

    Predaj delete(long id) throws EntityNotFoundException, EntityUndeletableException;

    void deleteAllByClient (Clients clients);
}
