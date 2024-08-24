package com.gbophuk0s.test.assignment.core.commandline.bank;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.commandline.CommandProcessor;
import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.service.BankService;
import com.gbophuk0s.test.assignment.support.Args;

public class CreateBankCommandProcessor implements CommandProcessor {

    private static final String NAME_PARAMETER = "name";
    private static final String LEGAL_ENTITY_CHARGE_PARAMETER = "legalEntityCharge";
    private static final String INDIVIDUAL_CHARGE_PARAMETER = "individualCharge";
    private static final double MIN_CHARGE_VALUE = 0;
    private static final double MAX_CHARGE_VALUE = 99;

    private final BankService bankService;

    public CreateBankCommandProcessor(BankService bankService) {
        super();

        this.bankService = bankService;
    }

    @Override
    public void process(Map<String, String> values) {
        Bank bank = processValues(values);

        bankService.create(bank);
    }

    private Bank processValues(Map<String, String> values) {
        Bank result = new Bank();

        result.setName(processName(values.get(NAME_PARAMETER)));
        result.setLegalEntityCharge(processLegalEntityCharge(values.get(LEGAL_ENTITY_CHARGE_PARAMETER)));
        result.setIndividualCharge(processIndividualCharge(values.get(INDIVIDUAL_CHARGE_PARAMETER)));

        return result;
    }

    private String processName(String value) {
        return Args.checkNotNull(value, "value");
    }

    private double processLegalEntityCharge(String value) {
        return validateCharge(value);
    }

    private double processIndividualCharge(String value) {
        return validateCharge(value);
    }

    private double validateCharge(String value) {
        Args.checkNotNull(value, "value");

        try {
            double result = Double.parseDouble(value);

            if (MIN_CHARGE_VALUE > result || result > MAX_CHARGE_VALUE) {
                throw new DataValidationException(String.format("Invalid value: %s", result));
            }

            return result;
        }
        catch (NumberFormatException e) {
            throw new DataValidationException("Couldn't parse value");
        }
    }
}
