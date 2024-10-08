package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.service.AccountService;

public class DeleteAccountCommandProcessor extends AbstractAccountCommandProcessor {

    public DeleteAccountCommandProcessor(AccountService accountService) {
        super(accountService);
    }

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=uuid",
            ID_PARAMETER
        );
    }

    @Override
    public void process(Map<String, String> values) {
        String id = processId(values.get(ID_PARAMETER));

        accountService.delete(id);
        logger.info("Deleted: {}", id);
    }

}
