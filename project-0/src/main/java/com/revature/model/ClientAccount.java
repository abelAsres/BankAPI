package com.revature.model;

import java.util.Objects;

public class ClientAccount {
    private String clientName;
    private String accountType;
    private double balance;

    public ClientAccount(){}

    public ClientAccount(String clientName, String accountType, double balance) {
        this.clientName = clientName;
        this.accountType = accountType;
        this.balance = balance;
    }

    public String getClientName() {
        return clientName;
    }

    public void setClientName(String clientName) {
        this.clientName = clientName;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
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
        ClientAccount that = (ClientAccount) o;
        return Double.compare(that.balance, balance) == 0 && Objects.equals(clientName, that.clientName) && Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(clientName, accountType, balance);
    }

    @Override
    public String toString() {
        return "ClientAccount{" +
                "clientName='" + clientName + '\'' +
                ", accountType='" + accountType + '\'' +
                ", balance=" + balance +
                '}';
    }
}
