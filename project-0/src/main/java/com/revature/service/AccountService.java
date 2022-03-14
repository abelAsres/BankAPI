package com.revature.service;

import com.revature.dao.AccountsDao;
import com.revature.model.Account;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.List;

public class AccountService {
    private AccountsDao accountsDao;

    public AccountService() {
        this.accountsDao = new AccountsDao();
    }

    public List<Account> getAllAccountByClientId(int clientId) throws SQLException {
        return accountsDao.getAllAccountByClientId(clientId);
    }
}
