package com.revature.service;

import com.revature.dao.AccountTypesDao;
import com.revature.model.AccountType;

import java.sql.SQLException;
import java.util.List;

public class AccountTypeService {
    private AccountTypesDao accountTypesDao;

    public AccountTypeService() {
        this.accountTypesDao = new AccountTypesDao();
    }

    public List<AccountType> getAllAccountTypes() throws SQLException {
        return accountTypesDao.getAllAccountTypes();
    }
    public AccountType updateAccountType(String accountTypeId, AccountType accountType) throws Exception {

        int id = Integer.parseInt(accountTypeId);
        //check if accountType exists
        if (accountTypesDao.getAccountById(id) == null){
            throw new Exception("Client with the id "+ accountTypeId+" does not exist in the database");
        }

        //validateClientInformation(accountType);
        accountType.setId(id);
        return this.accountTypesDao.updateAccountType(accountType);
    }

    public void validateAccountTypeInformation(AccountType accountType) {
        accountType.setAccountType(accountType.getAccountType().trim());

        if (!accountType.getAccountType().matches("[a-zA-Z?-]+")) {
            throw new IllegalArgumentException("First name must only have alphabetical characters. First name input was " + accountType.getAccountType());
        }
    }

    public boolean removeClient(String id) throws Exception {
        int accountTypeId = Integer.parseInt(id);

        if (accountTypesDao.getAccountById(accountTypeId) == null){
            throw new Exception("Client with the id "+ accountTypeId+" does not exist in the database");
        }
        return accountTypesDao.removeAccountType(accountTypeId);
    }

    public AccountType addAccountType(AccountType newAccountType) throws SQLException {
        validateAccountTypeInformation(newAccountType);
        return accountTypesDao.addAccountType(newAccountType);
    }
}
