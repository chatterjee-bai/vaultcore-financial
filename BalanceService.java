package com.vaultcore.vaultcore.service;


import com.vaultcore.vaultcore.ledger.LedgerEntry;
import com.vaultcore.vaultcore.ledger.LedgerRepository;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

@Service
public class BalanceService {

    private final LedgerRepository ledgerRepository;

    // Virtual Thread Executor (Java 21)
    private final ExecutorService virtualThreadExecutor =
            Executors.newVirtualThreadPerTaskExecutor();

    public BalanceService(LedgerRepository ledgerRepository) {
        this.ledgerRepository = ledgerRepository;
    }

    /**
     * Get account balance using virtual threads
     */
    public BigDecimal getBalance(Long accountId) {

        try {
            return virtualThreadExecutor.submit(() -> {

                List<LedgerEntry> entries =
                        ledgerRepository.findByAccountId(accountId);

                BigDecimal balance = BigDecimal.ZERO;

                for (LedgerEntry entry : entries) {
                    balance = balance
                            .add(entry.getCredit())
                            .subtract(entry.getDebit());
                }

                return balance;

            }).get();
        } catch (Exception e) {
            throw new RuntimeException("Failed to calculate balance", e);
        }
    }
}

