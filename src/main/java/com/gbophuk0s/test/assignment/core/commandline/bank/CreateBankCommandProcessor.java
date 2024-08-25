package com.gbophuk0s.test.assignment.core.commandline.bank;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.service.BankService;

public class CreateBankCommandProcessor extends AbstractBankCommandProcessor {

    public CreateBankCommandProcessor(BankService bankService) {
        super(bankService);
    }

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=name --%s=doubleValue --%s=doubleValue",
            NAME_PARAMETER,
            LEGAL_ENTITY_CHARGE_PARAMETER,
            INDIVIDUAL_CHARGE_PARAMETER
        );
    }

    @Override
    public void process(Map<String, String> values) {
        Bank bank = processValues(values);

        bank = bankService.create(bank);
        logger.info("Created: {}", bank);
    }

    private Bank processValues(Map<String, String> values) {
        Bank result = new Bank();

        result.setName(processName(values.get(NAME_PARAMETER)));
        result.setLegalEntityCharge(processLegalEntityCharge(values.get(LEGAL_ENTITY_CHARGE_PARAMETER)));
        result.setIndividualCharge(processIndividualCharge(values.get(INDIVIDUAL_CHARGE_PARAMETER)));

        return result;
    }

}
