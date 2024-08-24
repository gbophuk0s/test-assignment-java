package com.gbophuk0s.test.assignment.core.commandline.bank;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.service.BankService;

public class GetBankCommandProcessor extends AbstractBankCommandProcessor {

    public GetBankCommandProcessor(BankService bankService) {
        super(bankService);
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(ID_PARAMETER));
        Bank bank = bankService.getById(id);
        logger.info("Retrieved: {}", bank);
    }

}
