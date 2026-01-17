package com.vaultcore.vaultcore.service;


import com.vaultcore.vaultcore.ledger.LedgerEntry;
import com.vaultcore.vaultcore.ledger.LedgerRepository;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Isolation;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.UUID;

@Service
public class TransactionService {

    private final LedgerRepository ledgerRepository;

    public TransactionService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    /**
     * Transfers money from one account to another
     * ACID + SERIALIZABLE isolation enforced
     */
    @Transactional(isolation = Isolation.SERIALIZABLE)
    public void transfer(Long fromAccountId,
                         Long toAccountId,
                         BigDecimal amount) {

        if (amount.compareTo(BigDecimal.ZERO) <= 0) {
            throw new IllegalArgumentException("Transfer amount must be positive");
        }

        String reference = UUID.randomUUID().toString();

        // Debit entry (money out)
        LedgerEntry debitEntry = new LedgerEntry(
                fromAccountId,
                amount,
                BigDecimal.ZERO,
                reference
        );

        // Credit entry (money in)
        LedgerEntry creditEntry = new LedgerEntry(
                toAccountId,
                BigDecimal.ZERO,
                amount,
                reference
        );

        // Persist both entries atomically
        ledgerRepository.save(debitEntry);
        ledgerRepository.save(creditEntry);
    }
}
