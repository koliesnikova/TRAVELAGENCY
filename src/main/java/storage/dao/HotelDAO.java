package storage.dao;

import entity.Clients;
import entity.Hotel;
import entity.Type_umiestnenia;
import exeption.EntityNotFoundException;

import java.util.List;

public interface HotelDAO {

    List<Hotel> getAllHotels();

    Hotel getById(long id) throws EntityNotFoundException;

    Hotel save(Hotel hotel) throws EntityNotFoundException;
}
