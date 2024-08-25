package com.gbophuk0s.test.assignment.core.commandline;

import java.math.BigDecimal;
import java.util.Map;

import com.gbophuk0s.test.assignment.core.service.BankService;

public class TransferMoneyCommandProcessor extends AbstractCommandProcessor {

    private static final String FROM_ACCOUNT_ID_PARAMETER = "fromAccountId";
    private static final String TO_ACCOUNT_ID_PARAMETER = "toAccountId";
    private static final String AMOUNT_PARAMETER = "amount";

    private final BankService bankService;

    @Override
    public String getParamsTemplate() {
        return String.format("--%s=uuid --%s=uuid --%s=amount",
            FROM_ACCOUNT_ID_PARAMETER,
            TO_ACCOUNT_ID_PARAMETER,
            AMOUNT_PARAMETER
        );
    }

    public TransferMoneyCommandProcessor(BankService bankService) {
        super();

        this.bankService = bankService;
    }

    @Override
    public void process(Map<String, String> values) {
        String fromAccountId = processId(values.get(FROM_ACCOUNT_ID_PARAMETER));
        String toAccountId = processId(values.get(TO_ACCOUNT_ID_PARAMETER));
        BigDecimal amount = processBigDecimal(values.get(AMOUNT_PARAMETER));

        bankService.transfer(fromAccountId, toAccountId, amount);
    }

}
