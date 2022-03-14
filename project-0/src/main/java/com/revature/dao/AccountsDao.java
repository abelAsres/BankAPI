package com.revature.dao;

import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.service.ClientService;
import com.revature.ultility.ConnectionUtility;

import java.sql.*;
import java.util.ArrayList;
import java.util.List;

public class AccountsDao {
    public List<Account> getAllAccountByClientId(int clientId) throws SQLException {
        List<Account>clientAccounts = new ArrayList<>();
        try(Connection connection= ConnectionUtility.getConnection()){
            String query = "SELECT * FROM accounts WHERE accounts.client_id = ?";
            PreparedStatement pstmt = connection.prepareStatement(query);
            pstmt.setInt(1,clientId);

            ResultSet results = pstmt.executeQuery();

            while(results.next()){
                clientAccounts.add(new Account(results.getInt("id"),
                        results.getInt("client_id"),
                        results.getInt("account_type_id"),
                        results.getDouble("amount")));
            }
            pstmt.close();
            results.close();
        }
        return clientAccounts;

    }

    //getAccountWithMinMax
    //getAccountsWithMin
    //getAccountsWithMax
    //getAccountWithAccountId
    //updateAccount
    //removeAccount


}
