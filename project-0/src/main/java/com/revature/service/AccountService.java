package com.revature.service;

import com.revature.dao.AccountsDao;
import com.revature.dao.ClientDao;
import com.revature.exception.AccountDoesNotBelongToClient;
import com.revature.exception.ClientNotFoundException;
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

    public AccountService(AccountsDao mockAccountDao) {
        this.accountsDao = mockAccountDao;
    }

    public Account getAccountById(String accountId) throws SQLException {
        int id = Integer.parseInt(accountId);
        return accountsDao.getAccountById(id);
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

    public ClientAccount getClientAccountByAccountId(Client client,Account account) throws SQLException, AccountDoesNotBelongToClient {
        if (client.getId() != account.getClientId()){
            throw new AccountDoesNotBelongToClient("Account : "+ account.getId() + " does not belong to client with id: "+ client.getId());
        }
        return accountsDao.getClientAccountByAccountId(account.getId());
    }

    public Account updateClientAccountByAccountId(String accountId,Account account,String clientId) throws Exception {
        int aId = Integer.parseInt(accountId);
        int cId = Integer.parseInt(clientId);

        ClientDao clientDao = new ClientDao();
        if (clientDao.getClientByid(cId) == null){
            throw new ClientNotFoundException("Client with the id "+ cId +" does not exist in the database");
        }
        account.setClientId(cId);
        //check if client exists
        if (accountsDao.getClientAccountByAccountId(aId) == null){
            throw new AccountDoesNotBelongToClient("Account with the id "+ accountId +" does not exist in the database");
        }

        if (cId != account.getClientId()){
            throw new AccountDoesNotBelongToClient("Account : "+ aId + " does not belong to client with id: "+ cId);
        }
        account.setId(aId);
        return accountsDao.updateClientAccountByAccountId(account);
    }

    public boolean removeClientAccountById(Account account,Client client) throws AccountDoesNotBelongToClient, SQLException {
        if (account.getClientId() != client.getId()){
            throw new AccountDoesNotBelongToClient("Account with id: "+account.getClientId()+" does not belong to client with id: "+client.getId());
        }
        return accountsDao.removeClientAccountById(account.getId());
    }
}
