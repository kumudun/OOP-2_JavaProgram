package entity;

import jakarta.persistence.*;
import java.time.LocalDateTime;
import java.util.Objects;

/** Logs one conversion, e.g. "convert 100 EUR -> SEK". */
@Entity
@Table(name = "txn") // avoid reserved words
public class Transaction {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id; // DB auto-generates PK

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "source_code", referencedColumnName = "code", nullable = false)
    private Currency sourceCurrency;

    @ManyToOne(optional = false, fetch = FetchType.EAGER)
    @JoinColumn(name = "target_code", referencedColumnName = "code", nullable = false)
    private Currency targetCurrency;

    @Column(nullable = false)
    private double amount;       // input amount in source currency

    @Column(nullable = false)
    private double result;       // output amount in target currency

    // snapshot of the rates used for the calculation (for auditability)
    @Column(nullable = false)
    private double fromRateToUSD;

    @Column(nullable = false)
    private double toRateToUSD;

    @Column(nullable = false)
    private LocalDateTime createdAt;

    protected Transaction() { }

    public Transaction(Currency sourceCurrency,
                       Currency targetCurrency,
                       double amount,
                       double result,
                       double fromRateToUSD,
                       double toRateToUSD) {
        this.sourceCurrency = sourceCurrency;
        this.targetCurrency = targetCurrency;
        this.amount = amount;
        this.result = result;
        this.fromRateToUSD = fromRateToUSD;
        this.toRateToUSD = toRateToUSD;
        this.createdAt = LocalDateTime.now();
    }

    public Long getId() { return id; }
    public Currency getSourceCurrency() { return sourceCurrency; }
    public Currency getTargetCurrency() { return targetCurrency; }
    public double getAmount() { return amount; }
    public double getResult() { return result; }
    public double getFromRateToUSD() { return fromRateToUSD; }
    public double getToRateToUSD() { return toRateToUSD; }
    public LocalDateTime getCreatedAt() { return createdAt; }

    @Override public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Transaction)) return false;
        Transaction that = (Transaction) o;
        return Objects.equals(id, that.id);
    }
    @Override public int hashCode() { return Objects.hash(id); }
}
