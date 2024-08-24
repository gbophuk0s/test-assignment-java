package com.gbophuk0s.test.assignment.core.commandline;

import com.gbophuk0s.test.assignment.core.commandline.bank.CreateBankTaskProcessor;
import com.gbophuk0s.test.assignment.core.service.BankService;
import com.gbophuk0s.test.assignment.core.service.BankServiceImpl;

public class CommandlineConfig {

    private final BankService bankService;

    private final CommandlineArgsParser commandlineArgsParser;

    public CommandlineConfig() {
        this.bankService = new BankServiceImpl();
        this.commandlineArgsParser = new CommandlineArgsParser();
    }

    public CommandlineProcessor commandlineProcessor() {
        return new CommandlineProcessor(taskProcessorRegistry(), commandlineArgsParser);
    }

    private TaskProcessorRegistry taskProcessorRegistry() {
        TaskProcessorRegistry result = new TaskProcessorRegistry();

        registerProcessors(result);

        return result;
    }

    private void registerProcessors(TaskProcessorRegistry registry) {
        registry.register("createBank", new CreateBankTaskProcessor(bankService));
    }
}
