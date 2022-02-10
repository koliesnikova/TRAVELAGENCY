package sk.upjs.storage.dao;

import sk.upjs.entity.Druh_jedla;
import sk.upjs.exeption.EntityNotFoundException;

import java.util.List;

public interface Druh_jedlaDAO {
    Druh_jedla getById(long id) throws EntityNotFoundException;

    List<Druh_jedla> getAll();

    Druh_jedla save(Druh_jedla druh_jedla) throws EntityNotFoundException;

    Druh_jedla idByName(String s);
}
