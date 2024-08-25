package com.gbophuk0s.test.assignment.core.commandline;

import java.util.List;
import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public class GetClientAccountsCommandProcessor extends AbstractCommandProcessor {

    private static final String CLIENT_ID_PARAMETER = "clientId";

    private final AccountService accountService;

    public GetClientAccountsCommandProcessor(AccountService accountService) {
        super();

        this.accountService = accountService;
    }

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=uuid",
            CLIENT_ID_PARAMETER
        );
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(CLIENT_ID_PARAMETER));

        List<Account> accounts = accountService.findAllByClientId(id);

        logger.info("Retrieved client's accounts: {}", accounts.size());
        accounts.forEach(it -> {
            logger.info("Account: {}", it);
        });
    }

}
