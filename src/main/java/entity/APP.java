package entity;

import storage.dao.ClientsDAO;
import storage.DaoFactory;

public class APP {
     public static void main(String[] args) {
        ClientsDAO clientsDAO= DaoFactory.INSTANCE.getClientsDAO();
        Clients clients = clientsDAO.getById(1L);
         System.out.println(clients);
    }
}
