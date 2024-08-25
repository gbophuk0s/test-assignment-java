package com.gbophuk0s.test.assignment.core.commandline;

import com.gbophuk0s.test.assignment.core.commandline.account.CreateAccountCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.account.DeleteAccountCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.account.GetAccountCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.account.UpdateAccountCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.CreateBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.DeleteBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.GetBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.bank.UpdateBankCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.client.CreateClientCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.client.DeleteClientCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.client.GetClientCommandProcessor;
import com.gbophuk0s.test.assignment.core.commandline.client.UpdateClientCommandProcessor;
import com.gbophuk0s.test.assignment.core.service.AccountService;
import com.gbophuk0s.test.assignment.core.service.AccountServiceImpl;
import com.gbophuk0s.test.assignment.core.service.BankService;
import com.gbophuk0s.test.assignment.core.service.BankServiceImpl;
import com.gbophuk0s.test.assignment.core.service.ClientService;
import com.gbophuk0s.test.assignment.core.service.ClientServiceImpl;

public class CommandlineConfig {

    private final BankService bankService;

    private final ClientService clientService;

    private final AccountService accountService;

    private final CommandlineArgsParser commandlineArgsParser;

    public CommandlineConfig() {
        this.bankService = new BankServiceImpl();
        this.clientService = new ClientServiceImpl();
        this.accountService = new AccountServiceImpl();
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

        registry.register("createClient", new CreateClientCommandProcessor(clientService));
        registry.register("getClient", new GetClientCommandProcessor(clientService));
        registry.register("updateClient", new UpdateClientCommandProcessor(clientService));
        registry.register("deleteClient", new DeleteClientCommandProcessor(clientService));

        registry.register("createAccount", new CreateAccountCommandProcessor(accountService));
        registry.register("getAccount", new GetAccountCommandProcessor(accountService));
        registry.register("updateAccount", new UpdateAccountCommandProcessor(accountService));
        registry.register("deleteAccount", new DeleteAccountCommandProcessor(accountService));

        registry.register("transferMoney", new TransferMoneyCommandProcessor(bankService));
    }
}
