package com.gbophuk0s.test.assignment.core.commandline.bank;

import com.gbophuk0s.test.assignment.core.commandline.AbstractCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.service.BankService;

public abstract class AbstractBankCommandProcessor extends AbstractCommandProcessor {

    protected static final String ID_PARAMETER = "id";
    protected static final String NAME_PARAMETER = "name";
    protected static final String LEGAL_ENTITY_CHARGE_PARAMETER = "legalEntityCharge";
    protected static final String INDIVIDUAL_CHARGE_PARAMETER = "individualCharge";

    private static final int MAX_NAME_LENGTH = 25;
    private static final double MIN_CHARGE_VALUE = 0;
    private static final double MAX_CHARGE_VALUE = 99;

    protected final BankService bankService;

    protected AbstractBankCommandProcessor(BankService bankService) {
        this.bankService = bankService;
    }

    protected String processName(String value) {
        return processName(value, true);
    }

    protected String processName(String value, boolean required) {
        if (value == null && required) {
            throw new DataValidationException("Name is required");
        }

        if (value == null) {
            return null;
        }

        if (value.length() > MAX_NAME_LENGTH) {
            throw new DataValidationException(String.format("Max length of name is %s", MAX_NAME_LENGTH));
        }

        return value;
    }

    protected double processLegalEntityCharge(String value) {
        return validateCharge(value, LEGAL_ENTITY_CHARGE_PARAMETER);
    }

    protected double processIndividualCharge(String value) {
        return validateCharge(value, INDIVIDUAL_CHARGE_PARAMETER);
    }

    private double validateCharge(String value, String valueName) {
        return validateCharge(value, valueName, true);
    }

    protected Double validateCharge(String value, String valueName, boolean required) {
        if (value == null && required) {
            throw new DataValidationException(String.format("Value '%s' is required", valueName));
        }

        if (value == null) {
            return null;
        }

        try {
            double result = Double.parseDouble(value);

            if (MIN_CHARGE_VALUE > result || result > MAX_CHARGE_VALUE) {
                throw new DataValidationException(String.format("Invalid value: %s", result));
            }

            return result;
        }
        catch (NumberFormatException e) {
            throw new DataValidationException(e.getMessage());
        }
    }

}
