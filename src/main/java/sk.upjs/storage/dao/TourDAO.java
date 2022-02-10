package sk.upjs.storage.dao;

import sk.upjs.entity.Druh_jedla;
import sk.upjs.entity.Tour;
import sk.upjs.exeption.EntityNotFoundException;
import sk.upjs.exeption.EntityUndeletableException;

import java.util.List;

public interface TourDAO {

    List<Tour> getAll();

    Tour getById(long tour) throws EntityNotFoundException;


    Tour save(Tour tour) throws EntityNotFoundException;

    Tour delete(long id) throws EntityNotFoundException, EntityUndeletableException;

    void deleteAllByDruhJedla(Druh_jedla druh_jedla);
}
