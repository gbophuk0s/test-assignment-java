package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.AccountCompoundId;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public class CreateAccountCommandProcessor extends AbstractAccountCommandProcessor {

    public CreateAccountCommandProcessor(AccountService accountService) {
        super(accountService);
    }

    @Override
    public void process(Map<String, String> values) {
        Account account = processValues(values);

        account = accountService.create(account);
        logger.info("Created: {}", account);
    }

    private Account processValues(Map<String, String> values) {
        Account result = new Account();

        AccountCompoundId id = processCompoundId(values);

        result.setBankId(id.getBankId());
        result.setClientId(id.getClientId());
        result.setName(processName(values.get(NAME_PARAMETER)));
        result.setCurrency(id.getCurrency());

        return result;
    }

}
