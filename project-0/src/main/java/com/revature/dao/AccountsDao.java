package com.revature.dao;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.model.ClientAccount;
import com.revature.service.ClientService;
import com.revature.ultility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

//getAccountWithMinMax
//getAccountsWithMin
//getAccountsWithMax
//getAccountWithAccountId
//updateAccount
//removeAccount
public class AccountsDao {
    public List<ClientAccount> getAllAccountByClientId(int clientId) throws SQLException {
        List<ClientAccount>clientAccounts = new ArrayList<>();
        try(Connection connection= ConnectionUtility.getConnection()){
            String query = "SELECT c.first_name, c.last_name, at.account_type, a.balance FROM accounts a INNER JOIN " +
                    "clients c ON a.client_id = c.id INNER JOIN " +
                    "account_types at ON at.id = a.account_type_id WHERE a.client_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,clientId);

            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                clientAccounts.add(new ClientAccount(results.getString("first_name")+" "+ results.getString("last_name"),
                        results.getString("account_type"),
                        results.getDouble("balance")));
            }
            pstmt.close();
            results.close();
        }
        return clientAccounts;
    }

    public List<ClientAccount> getAllAccountByClientId(int clientId, int amountLessThan, int amountGreaterThan) throws SQLException {
        List<ClientAccount>clientAccounts = new ArrayList<>();
        String query;
        PreparedStatement pstmt;
        try(Connection connection= ConnectionUtility.getConnection()){
            if(amountGreaterThan == 0){
                query = "SELECT c.first_name, c.last_name, at.account_type, a.balance " +
                        "FROM accounts a INNER JOIN clients c ON a.client_id= c.id INNER JOIN " +
                        "account_types at ON at.id = a.account_type_id WHERE a.client_id = ? AND balance < ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1,clientId);
                pstmt.setInt(2,amountLessThan);
            }
            else if(amountLessThan == 0){
                query = "SELECT c.first_name, c.last_name, at.account_type, a.balance " +
                        "FROM accounts a INNER JOIN clients c ON a.client_id= c.id INNER JOIN account_types at ON " +
                        "at.id = a.account_type_id WHERE a.client_id = ? AND balance > ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1,clientId);
                pstmt.setInt(2,amountGreaterThan);
            }else{
                query =  "SELECT c.first_name, c.last_name, at.account_type, a.balance " +
                        "FROM accounts a INNER JOIN clients c ON a.client_id= c.id INNER JOIN account_types at ON " +
                        "at.id = a.account_type_id WHERE a.client_id = ? AND balance < ? AND balance > ?";
                pstmt = connection.prepareStatement(query);
                pstmt.setInt(1,clientId);
                pstmt.setInt(2,amountLessThan);
                pstmt.setInt(3,amountGreaterThan);
            }


            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                clientAccounts.add(new ClientAccount(results.getString("first_name")+" "+ results.getString("last_name"),
                        results.getString("account_type"),
                        results.getDouble("balance")));
            }
            pstmt.close();
            results.close();
        }
        return clientAccounts;
    }

    public Account getAccountById(int accountId) throws SQLException {

        try(Connection connection= ConnectionUtility.getConnection()){
            String query = "SELECT * FROM accounts WHERE id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,accountId);

            ResultSet result = pstmt.executeQuery();

            result.next();
            Account account = new Account(result.getInt("id"),
                    result.getInt("client_id"),
                    result.getInt("account_type_id"),
                    result.getDouble("balance"));

            pstmt.close();
            result.close();

            return account;
        }
    }

    public Account addAccountForClient(int clientId) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()) {
            String query = "INSERT INTO accounts(client_id) VALUES(?)";

            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setInt(1,clientId);

            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();

            result.next();
            int generatedId = result.getInt(1);

            pstmt.close();
            result.close();

            return  new Account(generatedId,clientId);
        }
    }

    public ClientAccount getClientAccountByAccountId(int accountId) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT c.first_name,c.last_name,at.account_type,a.balance" +
                    " FROM clients c JOIN accounts a ON c.id = a.client_id JOIN account_types at ON " +
                    "at.id= a.account_type_id WHERE a.id = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1,accountId);

            ResultSet result = pstmt.executeQuery();

            result.next();
            ClientAccount clientAccount = new ClientAccount(result.getString("first_name")+" "+result.getString("last_name"),
                    result.getString("account_type"),result.getDouble("balance"));
            pstmt.close();
            result.close();

            return clientAccount;
        }
    }

    public Account updateClientAccountByAccountId(Account account) throws SQLException {
        try(Connection connection=ConnectionUtility.getConnection()){
            String query = "UPDATE accounts SET balance=? WHERE id=?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setDouble(1,account.getBalance());
            pstmt.setInt(2,account.getId());

            pstmt.executeUpdate();
            pstmt.close();
        }
        return account;
    }

    public boolean removeClientAccountById (int accountId) throws SQLException {
        try (Connection connection = ConnectionUtility.getConnection()){
            String query = "DELETE FROM accounts WHERE id = ?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setInt(1,accountId);

            int deletedRecords = pstmt.executeUpdate();
            pstmt.close();

            if(deletedRecords == 1){
                return true;
            }
        }
        return false;
    }
}
