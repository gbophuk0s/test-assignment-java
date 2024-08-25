package com.gbophuk0s.test.assignment.core.commandline.bank;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.service.BankService;

public class FindAllBanksCommandProcessor extends AbstractBankCommandProcessor {

    public FindAllBanksCommandProcessor(BankService bankService) {
        super(bankService);
    }

    @Override
    public void process(Map<String, String> values) {
        bankService.findAll().forEach(it -> {
            logger.info("Bank: {}", it);
        });
    }

}
