package com.revature.model;

import java.util.Objects;

public class AccountType {
    private int id;
    private String accountType;

    public AccountType(int id, String accountType) {
        this.id = id;
        this.accountType = accountType;
    }

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getAccountType() {
        return accountType;
    }

    public void setAccountType(String accountType) {
        this.accountType = accountType;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        AccountType that = (AccountType) o;
        return id == that.id && Objects.equals(accountType, that.accountType);
    }

    @Override
    public int hashCode() {
        return Objects.hash(id, accountType);
    }

    @Override
    public String toString() {
        return "AccountType{" +
                "id=" + id +
                ", accountType='" + accountType + '\'' +
                '}';
    }
}
