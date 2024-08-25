package com.gbophuk0s.test.assignment.core.service;

import java.math.BigDecimal;
import java.sql.Connection;
import java.util.Objects;
import java.util.Optional;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.gbophuk0s.test.assignment.core.commandline.DataValidationException;
import com.gbophuk0s.test.assignment.core.db.ConnectionCallback;
import com.gbophuk0s.test.assignment.core.db.Database;
import com.gbophuk0s.test.assignment.core.db.TransactionTemplate;
import com.gbophuk0s.test.assignment.core.model.Account;
import com.gbophuk0s.test.assignment.core.model.Bank;
import com.gbophuk0s.test.assignment.core.model.Client;
import com.gbophuk0s.test.assignment.core.repository.BankRepository;
import com.gbophuk0s.test.assignment.core.repository.BankRepositoryImpl;

public class BankServiceImpl implements BankService {

    private final Logger LOGGER = LogManager.getLogger(BankServiceImpl.class);

    private final AccountService accountService;
    private final ClientServiceImpl clientService;
    private final TransactionTemplate transactionTemplate;
    private final BankRepository bankRepository;

    public BankServiceImpl() {
        this.clientService = new ClientServiceImpl();
        this.accountService = new AccountServiceImpl();
        this.transactionTemplate = new TransactionTemplate();
        this.bankRepository = new BankRepositoryImpl();
    }

    @Override
    public Bank create(Bank bankSpec) {
        return executeWithinTransaction(connection -> {
            return bankRepository.create(connection, bankSpec);
        });
    }

    @Override
    public Bank getById(String id) {
        return executeWithinTransaction(connection -> {
            return bankRepository.getById(connection, id);
        });
    }

    @Override
    public Optional<Bank> findById(String id) {
        return executeWithinTransaction(connection -> {
            return bankRepository.findById(connection, id);
        });
    }

    @Override
    public Bank update(String id, Bank bankSpec) {
        return executeWithinTransaction(connection -> {
            return bankRepository.update(connection, id, bankSpec);
        });
    }

    @Override
    public void deleteById(String id) {
        executeWithinTransaction(connection -> {
            bankRepository.deleteById(connection, id);

            return null;
        });
    }

    @Override
    public void transfer(String fromAccountId, String toAccountId, BigDecimal amount) {
        executeWithinTransaction(connection -> {
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

            BigDecimal charge =  BigDecimal.ZERO;

            if (!Objects.equals(fromBank, toBank)) {
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

    private <T> T executeWithinTransaction(ConnectionCallback<T> callback) {
        Connection connection = null;

        try {
            connection = Database.INSTANCE.openConnection();

            return transactionTemplate.execute(connection, callback);
        }
        finally {
            Database.INSTANCE.closeConnection(connection);
        }
    }

}
