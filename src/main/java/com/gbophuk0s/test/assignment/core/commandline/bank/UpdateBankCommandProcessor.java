package com.gbophuk0s.test.assignment.core.commandline.bank;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.service.BankService;

public class UpdateBankCommandProcessor extends AbstractBankCommandProcessor {

    public UpdateBankCommandProcessor(BankService bankService) {
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
        String id = processId(values.get(ID_PARAMETER));
        Bank spec = processValues(values);

        Bank bank = bankService.update(id, spec);
        logger.info("Updated: {}", bank);
    }

    private Bank processValues(Map<String, String> values) {
        Bank result = new Bank();

        result.setName(processName(values.get(NAME_PARAMETER), false));
        result.setLegalEntityCharge(validateCharge(
            values.get(LEGAL_ENTITY_CHARGE_PARAMETER), LEGAL_ENTITY_CHARGE_PARAMETER, false
        ));
        result.setIndividualCharge(validateCharge(
            values.get(INDIVIDUAL_CHARGE_PARAMETER), INDIVIDUAL_CHARGE_PARAMETER, false
        ));

        return result;
    }

}
