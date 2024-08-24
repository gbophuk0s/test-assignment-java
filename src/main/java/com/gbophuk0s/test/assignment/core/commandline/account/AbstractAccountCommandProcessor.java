package com.gbophuk0s.test.assignment.core.commandline.account;

import com.gbophuk0s.test.assignment.core.commandline.AbstractCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.model.Currency;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public abstract class AbstractAccountCommandProcessor extends AbstractCommandProcessor {

    protected static final String ID_PARAMETER = "id";
    protected static final String BANK_ID_PARAMETER = "bankId";
    protected static final String CLIENT_ID_PARAMETER = "clientId";
    protected static final String NAME_PARAMETER = "name";
    protected static final String CURRENCY_PARAMETER = "currency";

    protected final AccountService accountService;

    protected AbstractAccountCommandProcessor(AccountService accountService) {
        this.accountService = accountService;
    }

    protected Currency processCurrency(String value) {
        return processCurrency(value, true);
    }

    protected Currency processCurrency(String value, boolean required) {
        if (value == null && required) {
            throw new DataValidationException("Currency is required");
        }

        if (value == null) {
            return null;
        }

        return Currency.create(value);
    }
}
