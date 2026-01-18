package com.vaultcore.vaultcore.statement;

import org.springframework.stereotype.Service;

import java.time.LocalDate;

@Service
public class MonthlyStatementService {

    /**
     * Generate monthly statement (PDF simulation)
     */
    public void generateMonthlyStatement(Long accountId) {

        System.out.println("📄 Generating Monthly Statement");
        System.out.println("Account ID: " + accountId);
        System.out.println("Statement Month: " + LocalDate.now().getMonth());
        System.out.println("Statement Year: " + LocalDate.now().getYear());

        // Simulated PDF generation
        System.out.println("✔ Transactions fetched");
        System.out.println("✔ Balances calculated");
        System.out.println("✔ PDF generated successfully (mock)");
    }
}
