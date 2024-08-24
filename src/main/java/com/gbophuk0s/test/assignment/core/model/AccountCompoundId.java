package com.gbophuk0s.test.assignment.core.model;

import java.util.Objects;

public class AccountCompoundId {

    private final String bankId;
    private final String clientId;
    private final Currency currency;

    public AccountCompoundId(String bankId, String clientId, Currency currency) {
        this.bankId = bankId;
        this.clientId = clientId;
        this.currency = currency;
    }

    public String getBankId() {
        return bankId;
    }

    public String getClientId() {
        return clientId;
    }

    public Currency getCurrency() {
        return currency;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }

        if (o == null) {
            return false;
        }

        if (getClass() != o.getClass()) {
            return false;
        }

        AccountCompoundId that = (AccountCompoundId) o;

        return Objects.equals(this.getBankId(), that.getBankId())
            && Objects.equals(this.getClientId(), that.getClientId())
            && Objects.equals(this.getCurrency(), that.getCurrency());
    }

    @Override
    public int hashCode() {
        return Objects.hash(bankId, clientId, currency);
    }
}
