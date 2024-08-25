package com.gbophuk0s.test.assignment.core.model;

import java.math.BigDecimal;

public class Account extends AbstractDataObject {

    private String bankId;

    private String clientId;

    private Currency currency;

    private String name;

    private BigDecimal balance;

    public String getBankId() {
        return bankId;
    }

    public void setBankId(String bankId) {
        this.bankId = bankId;
    }

    public String getClientId() {
        return clientId;
    }

    public void setClientId(String clientId) {
        this.clientId = clientId;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public void withdraw(BigDecimal amount) {
        balance = balance.subtract(amount);
    }

    public void deposit(BigDecimal amount) {
        balance = balance.add(amount);
    }

    @Override
    public String toString() {
        return "Account{" +
            "id='" + getId() + '\'' +
            ", bankId='" + bankId + '\'' +
            ", clientId='" + clientId + '\'' +
            ", currency='" + currency + '\'' +
            ", name='" + name + '\'' +
            ", balance=" + balance +
            '}';
    }
}
