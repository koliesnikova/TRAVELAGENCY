package sk.upjs.storage.dao;

import sk.upjs.entity.Clients;
import sk.upjs.entity.Hotel;
import sk.upjs.entity.Type_umiestnenia;
import sk.upjs.exeption.EntityNotFoundException;

import java.util.List;

public interface HotelDAO {

    List<Hotel> getAllHotels();

    Hotel getById(long id) throws EntityNotFoundException;

    Hotel save(Hotel hotel) throws EntityNotFoundException;

    Hotel idByName(String s2);
}
