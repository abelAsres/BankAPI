package com.revature.service;

import com.revature.dao.AccountsDao;
import com.revature.dao.ClientDao;
import com.revature.model.Account;
import com.revature.model.Client;
import com.revature.model.ClientAccount;
import org.junit.jupiter.api.Assertions;
import org.junit.jupiter.api.Test;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import static org.mockito.Mockito.*;

public class AccountServiceTest {
    @Test
    public void getAccountById() throws SQLException {
        //Mock creation
        AccountsDao mockAccountDao = mock(AccountsDao.class);

        Account expectedAccount = new Account(1,2,3,10000);
        AccountService accountService = new AccountService(mockAccountDao);

        //Act
        //stubbing
        when(mockAccountDao.getAccountById(1)).thenReturn(expectedAccount);
        Account actualAccount = accountService.getAccountById("1");

        //Assert
        verify(mockAccountDao).getAccountById(1);
        Assertions.assertEquals(expectedAccount, actualAccount);
    }

    @Test
    public void getAllAccountByClientId() throws SQLException {
        //Mock creation
        AccountsDao mockAccountDao = mock(AccountsDao.class);

        List<ClientAccount> clientAccounts = new ArrayList<>();

        clientAccounts.add(new ClientAccount("A","A",100.0));
        clientAccounts.add(new ClientAccount("B","B",100.0));
        clientAccounts.add(new ClientAccount("C","C",100.0));
        clientAccounts.add(new ClientAccount("D","D",100.0));
        clientAccounts.add(new ClientAccount("E","E",100.0));

        //stubbing
        when(mockAccountDao.getAllAccountByClientId(1)).thenReturn(clientAccounts);

        AccountService accountService = new AccountService(mockAccountDao);

        //Act
        List<ClientAccount> actualClientAccounts = accountService.getAllAccountByClientId(1);

        //verification
        verify(mockAccountDao).getAllAccountByClientId(1);

        //Assert
        List<ClientAccount> expectedAccounts = new ArrayList<>(clientAccounts);
        Assertions.assertEquals(expectedAccounts, actualClientAccounts);
    }

    @Test
    public void getAllAccountByClientIdWithLessThan() throws SQLException {
        //Mock creation
        AccountsDao mockAccountDao = mock(AccountsDao.class);

        List<ClientAccount> clientAccounts = new ArrayList<>();

        clientAccounts.add(new ClientAccount("A","A",100.0));
        clientAccounts.add(new ClientAccount("B","B",100.0));
        clientAccounts.add(new ClientAccount("C","C",100.0));
        clientAccounts.add(new ClientAccount("D","D",100.0));
        clientAccounts.add(new ClientAccount("E","E",99.0));

        //stubbing
        when(mockAccountDao.getAllAccountByClientId(1,100,0)).thenReturn(clientAccounts);

        AccountService accountService = new AccountService(mockAccountDao);

        //Act
        List<ClientAccount> actualClientAccounts = accountService.getAllAccountByClientId(1,100,0);

        //verification
        verify(mockAccountDao).getAllAccountByClientId(1,100,0);

        //Assert
        List<ClientAccount> expectedAccounts = new ArrayList<>(clientAccounts);
        Assertions.assertEquals(expectedAccounts, actualClientAccounts);
    }

    @Test
    public void getAllAccountByClientIdWithGreaterThan() throws SQLException {
        //Mock creation
        AccountsDao mockAccountDao = mock(AccountsDao.class);

        List<ClientAccount> clientAccounts = new ArrayList<>();

        clientAccounts.add(new ClientAccount("A","A",100.0));
        clientAccounts.add(new ClientAccount("B","B",100.0));
        clientAccounts.add(new ClientAccount("C","C",100.0));
        clientAccounts.add(new ClientAccount("D","D",100.0));
        clientAccounts.add(new ClientAccount("E","E",99.0));

        //stubbing
        when(mockAccountDao.getAllAccountByClientId(1,0,100)).thenReturn(clientAccounts);

        AccountService accountService = new AccountService(mockAccountDao);

        //Act
        List<ClientAccount> actualClientAccounts = accountService.getAllAccountByClientId(1,0,100);

        //verification
        verify(mockAccountDao).getAllAccountByClientId(1,0,100);

        //Assert
        List<ClientAccount> expectedAccounts = new ArrayList<>(clientAccounts);
        Assertions.assertEquals(expectedAccounts, actualClientAccounts);
    }

    @Test
    public void getAllAccountByClientIdWithLessThanGreaterThan() throws SQLException {
        //Mock creation
        AccountsDao mockAccountDao = mock(AccountsDao.class);

        List<ClientAccount> clientAccounts = new ArrayList<>();

        clientAccounts.add(new ClientAccount("A","A",100.0));
        clientAccounts.add(new ClientAccount("B","B",100.0));
        clientAccounts.add(new ClientAccount("C","C",100.0));
        clientAccounts.add(new ClientAccount("D","D",100.0));
        clientAccounts.add(new ClientAccount("E","E",99.0));

        //stubbing
        when(mockAccountDao.getAllAccountByClientId(1,1000,100)).thenReturn(clientAccounts);

        AccountService accountService = new AccountService(mockAccountDao);

        //Act
        List<ClientAccount> actualClientAccounts = accountService.getAllAccountByClientId(1,1000,100);

        //verification
        verify(mockAccountDao).getAllAccountByClientId(1,1000,100);

        //Assert
        List<ClientAccount> expectedAccounts = new ArrayList<>(clientAccounts);
        Assertions.assertEquals(expectedAccounts, actualClientAccounts);
    }



}
