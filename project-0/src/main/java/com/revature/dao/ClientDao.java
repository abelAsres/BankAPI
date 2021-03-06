package com.revature.dao;

import com.revature.model.Client;
import com.revature.ultility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class ClientDao {
    //CRUD operations for clients
    //1. addClient
    //2. getClientById
    //3. getAllClients
    //4. updateClient
    //5. removeClient

    /*
    * Steps to completing a query
    * 1. try to connect to database
    * 2. create sql statement
    * 3. prepare the statement
    * 4. execute the query
    * 5. move cursor to end of result set with result.next()
    * 6. handle anything returned from the DB
    * */
    public Client addClient(Client client) throws SQLException {


        try(Connection connection = ConnectionUtility.getConnection()) {
            String query = "INSERT INTO clients(first_name,last_name) VALUES(?,?)";

            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,client.getFirst_name());
            pstmt.setString(2,client.getLast_name());

            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();

            result.next();
            int generatedId = result.getInt(1);
            pstmt.close();
            result.close();
            return new Client(generatedId,client.getFirst_name(),client.getLast_name());
        }
    }

    public boolean isClientInDatabase(Client client) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM clients WHERE first_name = ? AND last_name = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1,client.getFirst_name());
            pstmt.setString(2,client.getLast_name());

            ResultSet result = pstmt.executeQuery();
            result.next();
            int clients = result.getRow();
            result.close();
            if (clients == 1){
                return true;
            }

            return false;
        }

    }

    public List<Client> getAllClients() throws SQLException {
        //create list to retrieve all of the clients
        List<Client> clients = new ArrayList<>();

        try(Connection connection=ConnectionUtility.getConnection()){
            String query = "SELECT * FROM clients";

            PreparedStatement pstmt = connection.prepareStatement(query);

            ResultSet results = pstmt.executeQuery();

            //add all the rows of the results to the clients arraylist
            while(results.next()){
                clients.add(new Client(results.getInt("id"),
                        results.getString("first_name"),
                        results.getString("last_name")));
            }

            pstmt.close();
            results.close();
        }
        return clients;
    }

    public Client getClientByid(int id) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            String query = "SELECT * FROM clients WHERE clients.id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,id);

            ResultSet results = pstmt.executeQuery();
            if(results.next()){
                int clientId = results.getInt(1);
                String clientFirstName = results.getString("first_name");
                String clientLastName = results.getString("last_name");
                pstmt.close();
                results.close();
                return new Client(clientId,clientFirstName,clientLastName);
            }
        }
        return null;
    }

    public Client updateClient(Client client) throws SQLException {
        try(Connection connection=ConnectionUtility.getConnection()){
             String query = "UPDATE clients SET first_name=?, last_name=? WHERE clients.id=?";

             PreparedStatement pstmt = connection.prepareStatement(query);

             pstmt.setString(1,client.getFirst_name());
             pstmt.setString(2,client.getLast_name());
             pstmt.setInt(3,client.getId());

             pstmt.executeUpdate();
             pstmt.close();
        }
        return client;
    }

    public boolean removeClient(int id) throws SQLException {
        try(Connection connection=ConnectionUtility.getConnection()){
            String query = "DELETE FROM clients WHERE clients.id = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1,id);
            int deletedClients = pstmt.executeUpdate();
            pstmt.close();

            if(deletedClients == 1){
                return true;
            }
        }
        return false;
    }
}
