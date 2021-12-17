package storage.dao;

import entity.Tour;
import exeption.EntityNotFoundException;
import exeption.EntityUndeletableException;
import org.springframework.jdbc.core.JdbcTemplate;

import java.util.List;

public interface TourDAO {

    List<Tour> getAll();

    Tour getById(long tour) throws EntityNotFoundException;


    Tour save(Tour tour) throws EntityNotFoundException;

    Tour delete(long id) throws EntityNotFoundException, EntityUndeletableException;
}
