package com.vaultcore.vaultcore.controller;

import com.vaultcore.vaultcore.ledger.LedgerEntry;
import com.vaultcore.vaultcore.ledger.LedgerRepository;
import com.vaultcore.vaultcore.service.TransactionService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;
import java.util.List;

@RestController
@RequestMapping("/api/ledger")
public class LedgerController {

    private final TransactionService transactionService;
    private final LedgerRepository ledgerRepository;

    public LedgerController(TransactionService transactionService,
                            LedgerRepository ledgerRepository) {
        this.transactionService = transactionService;
        this.ledgerRepository = ledgerRepository;
    }

    /**
     * Send money from one account to another
     */
    @PostMapping("/transfer")
    public ResponseEntity<String> transferMoney(
            @RequestParam Long fromAccountId,
            @RequestParam Long toAccountId,
            @RequestParam BigDecimal amount) {

        transactionService.transfer(fromAccountId, toAccountId, amount);
        return ResponseEntity.ok("Transfer successful");
    }

    /**
     * Get all ledger entries for an account
     */
    @GetMapping("/entries/{accountId}")
    public ResponseEntity<List<LedgerEntry>> getLedgerEntries(
            @PathVariable Long accountId) {

        List<LedgerEntry> entries = ledgerRepository.findByAccountId(accountId);
        return ResponseEntity.ok(entries);
    }
}


