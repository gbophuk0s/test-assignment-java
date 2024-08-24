package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.commandline.AbstractCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.model.AccountCompoundId;
import com.gbophuk0s.test.assignment.core.model.Currency;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public abstract class AbstractAccountCommandProcessor extends AbstractCommandProcessor {
    protected static final String BANK_ID_PARAMETER = "bankId";
    protected static final String CLIENT_ID_PARAMETER = "clientId";
    protected static final String CURRENCY_PARAMETER = "currency";

    protected final AccountService accountService;

    protected AbstractAccountCommandProcessor(AccountService accountService) {
        this.accountService = accountService;
    }

    protected AccountCompoundId processCompoundId(Map<String, String> values) {
        String bankId = processId(values.get(BANK_ID_PARAMETER));
        String clientId = processId(values.get(CLIENT_ID_PARAMETER));
        Currency currency = processCurrency(values.get(CURRENCY_PARAMETER));

        return new AccountCompoundId(bankId, clientId, currency);
    }

    private Currency processCurrency(String value) {
        return processCurrency(value, true);
    }

    private Currency processCurrency(String value, boolean required) {
        if (value == null && required) {
            throw new DataValidationException("Currency is required");
        }

        if (value == null) {
            return null;
        }

        return Currency.create(value);
    }
}
