package com.vaultcore.vaultcore.controller;


import com.vaultcore.vaultcore.service.BalanceService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.math.BigDecimal;

@RestController
@RequestMapping("/api/balance")
public class BalanceController {

    private final BalanceService balanceService;

    public BalanceController(BalanceService balanceService) {
        this.balanceService = balanceService;
    }

    /**
     * Get account balance
     */
    @GetMapping("/{accountId}")
    public ResponseEntity<BigDecimal> getBalance(
            @PathVariable Long accountId) {

        BigDecimal balance = balanceService.getBalance(accountId);
        return ResponseEntity.ok(balance);
    }
}

