package com.revature.dao;

import com.revature.model.AccountType;
import com.revature.ultility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountTypesDao {
  //addAcountType
  //removeAccountType
  //updateAccountType
  //getAccountTypeById

    public List<AccountType> getAllAccountTypes() throws SQLException {
        List<AccountType> accountTypes = new ArrayList<>();
        try(Connection connection = ConnectionUtility.getConnection()){
            String query = "SELECT * FROM account_types";

            PreparedStatement pstmt = connection.prepareStatement(query);

            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                accountTypes.add(new AccountType(results.getInt("id"),
                        results.getString("account_type")));
            }
            pstmt.close();
            results.close();
        }
        return accountTypes;
    }
    public AccountType getAccountById(int id) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            String query = "SELECT * FROM account_types WHERE account_types.id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,id);

            ResultSet results = pstmt.executeQuery();
            if(results.next()){
                int accountTypeId = results.getInt(1);
                String accountType = results.getString("account_type");
                pstmt.close();
                results.close();
                return new AccountType(accountTypeId,accountType);
            }
        }
        return null;
    }

    public boolean removeAccountType (int id) throws SQLException {
        try(Connection connection= ConnectionUtility.getConnection()){
            String query = "DELETE FROM account_types WHERE account_types.id = ?";

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

    public AccountType updateAccountType(AccountType accountType) throws SQLException {
        try(Connection connection=ConnectionUtility.getConnection()){
            String query = "UPDATE account_types SET account_type=? WHERE account_types.id=?";

            PreparedStatement pstmt = connection.prepareStatement(query);

            pstmt.setString(1,accountType.getAccountType());
            pstmt.setInt(2,accountType.getId());

            pstmt.executeUpdate();
            pstmt.close();
        }
        return accountType;
    }

    public AccountType addAccountType(AccountType accountType) throws SQLException {
        try(Connection connection = ConnectionUtility.getConnection()) {
            String query = "INSERT INTO accountTypes(first_name,last_name) VALUES(?,?)";

            PreparedStatement pstmt = connection.prepareStatement(query, Statement.RETURN_GENERATED_KEYS);

            pstmt.setString(1,accountType.getAccountType());

            pstmt.executeUpdate();

            ResultSet result = pstmt.getGeneratedKeys();

            result.next();
            int generatedId = result.getInt(1);
            pstmt.close();
            result.close();
            return new AccountType(generatedId,accountType.getAccountType());
        }
    }

}
