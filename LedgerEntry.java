package com.vaultcore.vaultcore.ledger;
import jakarta.persistence.*;
import java.math.BigDecimal;
import java.time.LocalDateTime;

@Entity
@Table(
    name = "ledger_entries",
    indexes = {
        @Index(name = "idx_account_id", columnList = "accountId")
    }
)
public class LedgerEntry {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(nullable = false)
    private Long accountId;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal debit;

    @Column(nullable = false, precision = 19, scale = 4)
    private BigDecimal credit;

    @Column(nullable = false)
    private String transactionReference;

    @Column(nullable = false, updatable = false)
    private LocalDateTime createdAt;

    protected LedgerEntry() {
        // JPA only
    }

    public LedgerEntry(Long accountId,
                       BigDecimal debit,
                       BigDecimal credit,
                       String transactionReference) {

        this.accountId = accountId;
        this.debit = debit;
        this.credit = credit;
        this.transactionReference = transactionReference;
        this.createdAt = LocalDateTime.now();
    }

    // 🔒 NO SETTERS → IMMUTABLE LEDGER

    public Long getId() {
        return id;
    }

    public Long getAccountId() {
        return accountId;
    }

    public BigDecimal getDebit() {
        return debit;
    }

    public BigDecimal getCredit() {
        return credit;
    }

    public String getTransactionReference() {
        return transactionReference;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }
}
