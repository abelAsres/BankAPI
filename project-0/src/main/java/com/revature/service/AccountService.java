package com.revature.service;

import com.revature.dao.AccountsDao;
import com.revature.dao.ClientDao;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.model.ClientAccount;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountsDao accountsDao;



    public AccountService() {
        this.accountsDao = new AccountsDao();
    }

    public List<ClientAccount> getAllAccountByClientId(int clientId) throws SQLException {
        return accountsDao.getAllAccountByClientId(clientId);
    }

    public List<ClientAccount> getAllAccountByClientId(int clientId, int amountLessThan, int amountGreaterThan) throws SQLException {
        return accountsDao.getAllAccountByClientId(clientId,amountLessThan,amountGreaterThan);
    }

    public Account addAccountForClient(int clientId) throws SQLException {
        return accountsDao.addAccountForClient(clientId);
    }

    public ClientAccount getClientAccountByAccountId(String accountId) throws SQLException {
        return accountsDao.getClientAccountByAccountId(Integer.parseInt(accountId));
    }

    public Account updateClientAccountByAccountId(String accountId,Account account,String clientId) throws Exception {
        int aId = Integer.parseInt(accountId);
        int cId = Integer.parseInt(clientId);

        ClientDao clientDao = new ClientDao();
        if (clientDao.getClientByid(cId) == null){
            throw new Exception("Client with the id "+ cId +" does not exist in the database");
        }
        account.setClientId(cId);
        //check if client exists
        if (accountsDao.getClientAccountByAccountId(aId) == null){
            throw new Exception("Client with the id "+ accountId +" does not exist in the database");
        }
        account.setId(aId);
        return accountsDao.updateClientAccountByAccountId(account);
    }
}
