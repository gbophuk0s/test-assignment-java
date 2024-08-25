package com.gbophuk0s.test.assignment.core.commandline.bank;

import java.util.Map;

import com.gbophuk0s.test.assignment.core.service.BankService;

public class DeleteBankCommandProcessor extends AbstractBankCommandProcessor {

    public DeleteBankCommandProcessor(BankService bankService) {
        super(bankService);
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

        bankService.delete(id);
        logger.info("Deleted: {}", id);
    }

}
