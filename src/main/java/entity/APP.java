package entity;

import storage.ClientsDAO;
import storage.DaoFactory;

import java.util.List;

public class APP {
     public static void main(String[] args) {
        ClientsDAO clientsDAO= DaoFactory.INSTANCE.getClientsDAO();
         List<Clients> clients = clientsDAO.getAll();
         System.out.println(clients);
    }
}
