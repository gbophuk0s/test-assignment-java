package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public class UpdateAccountCommandProcessor extends AbstractAccountCommandProcessor {

    public UpdateAccountCommandProcessor(AccountService accountService) {
        super(accountService);
    }

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=uuid --%s=uuid --%s=currencyCode --%s=name --%s=balance",
            BANK_ID_PARAMETER,
            CLIENT_ID_PARAMETER,
            CURRENCY_PARAMETER,
            NAME_PARAMETER,
            BALANCE_PARAMETER
        );
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(ID_PARAMETER));

        Account spec = processValues(values);

        Account account = accountService.update(id, spec);
        logger.info("Updated: {}", account);
    }

    private Account processValues(Map<String, String> values) {
        Account result = new Account();

        result.setCurrency(processCurrency(values.get(CURRENCY_PARAMETER), false));
        result.setName(processName(values.get(NAME_PARAMETER), false));
        result.setBalance(processBigDecimal(values.get(BALANCE_PARAMETER), false));

        return result;
    }

}
