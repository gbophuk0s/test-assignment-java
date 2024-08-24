package com.gbophuk0s.test.assignment.core.model;

public class Account extends AbstractDataObject {

    private String bankId;

    private String clientId;

    private String name;

    private Currency currency;

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

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public Currency getCurrency() {
        return currency;
    }

    public void setCurrency(Currency currency) {
        this.currency = currency;
    }

    @Override
    public String toString() {
        return "Account{" +
            "id='" + getId() + '\'' +
            ", bankId='" + bankId + '\'' +
            ", clientId='" + clientId + '\'' +
            ", name='" + name + '\'' +
            ", currency=" + currency +
            '}';
    }
}
