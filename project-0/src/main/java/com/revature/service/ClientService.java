package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientDao clientDao;

    public ClientService() {
        this.clientDao = clientDao;
    }

    public ClientService(ClientDao mockClientDao) {
        this.clientDao = mockClientDao;
    }

    public ClientDao getClientDao() {
        return clientDao;
    }

    public void setClientDao(ClientDao clientDao) {
        this.clientDao = clientDao;
    }

    public List<Client> getAllClients() throws SQLException {
        return this.clientDao.getAllClients();
    }

    public Client getClientById(String id) throws SQLException,Exception {
        try {
            // This could throw an unchecked exception
            // known as NumberFormatException
            // Important to take note of this, because any unhandled exceptions will result
            // in a 500 Internal Server Error (which we should try to avoid)
            int clientId = Integer.parseInt(id);

            Client client = this.clientDao.getClientByid(clientId);

            if(client == null){
                throw new Exception("Client with id "+id+" does not exist in DB");
            }

            return client;
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("The client id provided was invalid");
        }
    }
}
