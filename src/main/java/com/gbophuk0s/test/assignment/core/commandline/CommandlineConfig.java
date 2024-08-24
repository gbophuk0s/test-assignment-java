package com.gbophuk0s.test.assignment.core.commandline;

import com.gbophuk0s.test.assignment.core.commandline.bank.CreateBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.DeleteBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.GetBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.UpdateBankCommandProcessor;
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

    private CommandProcessorRegistry taskProcessorRegistry() {
        CommandProcessorRegistry result = new CommandProcessorRegistry();

        registerProcessors(result);

        return result;
    }

    private void registerProcessors(CommandProcessorRegistry registry) {
        registry.register("createBank", new CreateBankCommandProcessor(bankService));
        registry.register("getBank", new GetBankCommandProcessor(bankService));
        registry.register("updateBank", new UpdateBankCommandProcessor(bankService));
        registry.register("deleteBank", new DeleteBankCommandProcessor(bankService));
    }
}
