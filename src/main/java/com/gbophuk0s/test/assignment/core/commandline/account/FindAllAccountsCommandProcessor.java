package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.service.AccountService;

public class FindAllAccountsCommandProcessor extends AbstractAccountCommandProcessor {

    public FindAllAccountsCommandProcessor(AccountService accountService) {
        super(accountService);
    }

    @Override
    public void process(Map<String, String> values) {
        accountService.findAll().forEach(it -> {
            logger.info("Account: {}", it);
        });
    }

}
