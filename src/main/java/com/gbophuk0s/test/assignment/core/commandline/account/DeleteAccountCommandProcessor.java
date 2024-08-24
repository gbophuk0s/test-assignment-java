package com.gbophuk0s.test.assignment.core.commandline.account;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.model.AccountCompoundId;
import com.gbophuk0s.test.assignment.core.service.AccountService;

public class DeleteAccountCommandProcessor extends AbstractAccountCommandProcessor {

    public DeleteAccountCommandProcessor(AccountService accountService) {
        super(accountService);
    }

    @Override
    public void process(Map<String, String> values) {
        AccountCompoundId id = processCompoundId(values);

        accountService.deleteById(id);
        logger.info("Deleted: {}", id);
    }

}
