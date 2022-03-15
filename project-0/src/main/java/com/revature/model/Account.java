package com.revature.model;

import java.util.Objects;

public class Account {
    private int id;
    private int clientId;
    private int accountTypeId;
    private double balance;

    public Account(){}

    public Account(int id, int clientId){
        this.id = id;
        this.clientId = clientId;
        this.accountTypeId = 1;
        this.balance = 0;
    }
    public Account(int id, int clientId, int accountTypeId, double balance) {
        this.id = id;
        this.clientId = clientId;
        this.accountTypeId = accountTypeId;
        this.balance = balance;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public int getClientId() {
        return clientId;
    }

    public void setClientId(int clientId) {
        this.clientId = clientId;
    }

    public int getAccountTypeId() {
        return accountTypeId;
    }

    public void setAccountTypeId(int accountTypeId) {
        this.accountTypeId = accountTypeId;
    }

    public double getBalance() {
        return balance;
    }

    public void setBalance(double balance) {
        this.balance = balance;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Account account = (Account) o;
        return id == account.id && clientId == account.clientId && accountTypeId == account.accountTypeId && Double.compare(account.balance, balance) == 0;
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, clientId, accountTypeId, balance);
    }

    @Override
    public String toString() {
        return "Account{" +
                "id=" + id +
                ", clientId=" + clientId +
                ", accountTypeId=" + accountTypeId +
                ", balance=" + balance +
                '}';
    }
}
