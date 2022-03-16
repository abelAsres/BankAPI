package com.revature.service;

import com.revature.dao.ClientDao;
import com.revature.exception.ClientAlreadyExistsException;
import com.revature.exception.ClientNotFoundException;
import com.revature.model.Client;

import java.sql.SQLException;
import java.util.List;

public class ClientService {
    private ClientDao clientDao;

    public ClientService() {
        this.clientDao = new ClientDao();
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

    public Client getClientById(String id) throws SQLException,ClientNotFoundException {
        try {
            // This could throw an unchecked exception
            // known as NumberFormatException
            // Important to take note of this, because any unhandled exceptions will result
            // in a 500 Internal Server Error (which we should try to avoid)
            Client client = this.clientDao.getClientByid(Integer.parseInt(id));

            if(client == null){
                throw new ClientNotFoundException("Client with id "+id+" does not exist in DB");
            }

            return client;
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("The client id provided was invalid");
        }
    }

    public Client updateClient(String clientId,Client client) throws ClientNotFoundException, SQLException {

        try{
            int id = Integer.parseInt(clientId);
            //check if client exists
            if (clientDao.getClientByid(id) == null){
                throw new ClientNotFoundException("Client with the id "+ clientId+" does not exist in the database");
            }

            validateClientInformation(client);
            client.setId(id);

            return this.clientDao.updateClient(client);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("The client id provided was invalid");
        }
    }

    public void validateClientInformation(Client client){
        try{
            client.setFirst_name(client.getFirst_name().trim());
            client.setLast_name(client.getLast_name().trim());

            if (!client.getFirst_name().matches("[a-zA-Z?']+")) {
                throw new IllegalArgumentException("First name must only have alphabetical characters. First name input was " + client.getFirst_name());
            }

            if (!client.getLast_name().matches("[a-zA-Z?']+")) {
                throw new IllegalArgumentException("Last name must only have alphabetical characters. Last name input was " + client.getLast_name());
            }
        }catch(NullPointerException e){
            new NullPointerException("Parameter specified as non-null is null");
        }

    }

    public boolean removeClient(String id) throws ClientNotFoundException, SQLException {
        try{
            int clientId = Integer.parseInt(id);

            if (clientDao.getClientByid(clientId) == null){
                throw new ClientNotFoundException("Client with the id "+ clientId+" does not exist in the database");
            }
            return clientDao.removeClient(clientId);
        }catch(NumberFormatException e){
            throw new IllegalArgumentException("The client id provided was invalid");
        }
    }

    public Client addClient(Client newClient) throws SQLException, ClientAlreadyExistsException {
       if(clientDao.isClientInDatabase(newClient)){
            throw new ClientAlreadyExistsException(newClient.getFirst_name()+" "+newClient.getLast_name()+" is already a client");
       }else{
           validateClientInformation(newClient);
           return clientDao.addClient(newClient);
       }
    }
}
