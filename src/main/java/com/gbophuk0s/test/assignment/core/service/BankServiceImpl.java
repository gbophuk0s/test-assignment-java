package com.gbophuk0s.test.assignment.core.service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Objects;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.repository.BankRepository;
import com.gbophuk0s.test.assignment.core.repository.BankRepositoryImpl;

public class BankServiceImpl extends AbstractService implements BankService {

    private final Logger LOGGER = LogManager.getLogger(BankServiceImpl.class);

    private final AccountService accountService;
    private final ClientServiceImpl clientService;
    private final BankRepository bankRepository;

    public BankServiceImpl() {
        super();

        this.clientService = new ClientServiceImpl();
        this.accountService = new AccountServiceImpl();
        this.bankRepository = new BankRepositoryImpl();
    }

    @Override
    public List<Bank> findAll() {
        return newTransaction(connection -> {
            return bankRepository.findAll(connection);
        });
    }

    @Override
    public Bank create(Bank bankSpec) {
        return newTransaction(connection -> {
            return bankRepository.create(connection, bankSpec);
        });
    }

    @Override
    public Bank getById(String id) {
        return newTransaction(connection -> {
            return bankRepository.getById(connection, id);
        });
    }

    @Override
    public Bank update(String id, Bank bankSpec) {
        return newTransaction(connection -> {
            return bankRepository.update(connection, id, bankSpec);
        });
    }

    @Override
    public void delete(String id) {
        newTransaction(connection -> {
            bankRepository.deleteById(connection, id);

            return null;
        });
    }

    @Override
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        newTransaction(connection -> {
            Account fromAccount = accountService.getById(fromAccountId);
            Account toAccount = accountService.getById(toAccountId);
            LOGGER.info(
                "Transferring money:\nfromAccount = {},\ntoAccount = {},\namount = {}", fromAccount, toAccount, amount
            );

            if (!Objects.equals(fromAccount.getCurrency(), toAccount.getCurrency())) {
                throw new DataValidationException("Currencies do not match");
            }

            Bank fromBank = getById(fromAccount.getBankId());
            Bank toBank = getById(toAccount.getBankId());

            BigDecimal charge = BigDecimal.ZERO;

            if (!Objects.equals(fromBank, toBank)) {
                LOGGER.info("Calculating a transfer charge to another bank: {}", toBank);
                Client fromClient = clientService.getById(fromAccount.getClientId());

                charge = Client.Type.LEGAL_ENTITY.equals(fromClient.getType())
                    ? BigDecimal.valueOf(fromBank.getLegalEntityCharge())
                    : BigDecimal.valueOf(fromBank.getIndividualCharge());
            }

            BigDecimal finalAmount = amount.add(amount.multiply(charge));

            if (fromAccount.getBalance().compareTo(finalAmount) < 0) {
                throw new DataValidationException("Not enough money");
            }

            LOGGER.info("Charge: {}", charge);

            fromAccount.withdraw(finalAmount);
            toAccount.deposit(amount);

            LOGGER.info("Withdrawn: {}", finalAmount);
            LOGGER.info("Deposited: {}", amount);

            accountService.update(fromAccount.getId(), fromAccount);
            accountService.update(toAccount.getId(), toAccount);

            return null;
        });
    }

}
