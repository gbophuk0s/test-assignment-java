package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public class GetAccountCommandProcessor extends AbstractAccountCommandProcessor {

    public GetAccountCommandProcessor(AccountService accountService) {
        super(accountService);
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(ID_PARAMETER));

        Account account = accountService.getById(id);
        logger.info("Retrieved: {}", account);
    }

}
