package storage.dao;

import entity.Druh_jedla;
import exeption.EntityNotFoundException;

public interface Druh_jedlaDAO {
    Druh_jedla getById(long id) throws EntityNotFoundException;

    Druh_jedla save(Druh_jedla druh_jedla) throws EntityNotFoundException;
}
