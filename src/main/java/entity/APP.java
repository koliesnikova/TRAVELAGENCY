package entity;

import storage.dao.ClientsDAO;
import storage.DaoFactory;
import storage.dao.HotelDAO;
import storage.dao.PredajDAO;
import storage.dao.TourDAO;

import java.util.List;

public class APP {
     public static void main(String[] args) {
       // ClientsDAO clientsDAO= DaoFactory.INSTANCE.getClientsDAO();
       // Clients clients = clientsDAO.getById(1L);
       //  System.out.println(clients);
       //  Druh_jedla druh_jedla = DaoFactory.INSTANCE.getDruh_jedlaDAO().getById(1);
       //  System.out.println(druh_jedla);
       //   HotelDAO hotelDAO = DaoFactory.INSTANCE.getHotelDAO();
         //Hotel hotel = hotelDAO.getById(2L);
         PredajDAO predajDAO = DaoFactory.INSTANCE.getPredajDAO();
         Predaj predaj = predajDAO.getById(1L);

        // List<Predaj> predajs = predajDAO.getAll();
         System.out.println(predaj);




    }
}
